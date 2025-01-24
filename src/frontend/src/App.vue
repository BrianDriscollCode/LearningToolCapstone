<template>
  <div class="appContainer">
    <NavBar />

    <div :class="['contentContainer', { 'overflowAllowed': overflowPage }]">
      <RouterView />
    </div>

  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import { computed  } from "vue";
import { RouterView, useRoute, useRouter } from 'vue-router';
import { supabase } from "./clients/supabase";
import { useAccountStore } from "./stores/account";
import { config } from "@/config"

const route = useRoute();
const router = useRouter();
const account = useAccountStore();

// define which container styling to use depending on route (overflow hidden or not)
const overflowPage = computed(() => 
  route.name === "home" ||
  route.name === "userHome" || 
  route.name === "dataPage" ||
  route.name === "schedule" ||
  route.name === "learningMap" ||
  route.name === "dashboard" ||
  route.name === "manageTopic" ||
  route.name === "search" ||
  route.name === "generateReport"
)


// Initialize supabase auth and connect with user account in database
const initSession = async () => 
{
  // Normal session getting
  const { data: { session }, error } = await supabase.auth.getSession();
  if (session)
  {
    processSession(session, error);
  }

  // On Sign Up, get the url token to overcome hash routing error with supabase
  parseURL();
  
  
}

initSession();

const processSession = async (session, error) => 
{
  if (session)
  {
    if (config.debug) console.log("APP: Session is active - ", session);
    account.status = true;
    let uuid = session.user.id; 
    try
    {
      const response = await fetch(`/api/users/by-uuid/${uuid}`);
      const userData = await response.json();
      
      if (config.debug) console.log("APP: User data - ", userData);
      
      account.name = userData.name;
      account.uuid = uuid;
      account.onboardingFinished = userData.onboardingFinished;
      account.initialDataGenerated = userData.initialDataGenerated;
      if (userData.employee)
      {
          account.isAdmin = userData.employee.is_Admin;
      }
      else
      {
          account.isAdmin = false;
      }
    }
    catch (tryError)
    {
      if (config.debug) console.log("APP: " + tryError);
      const { error } = await supabase.auth.signOut();

      if (error)
      {
        if (config.debug) console.log(error);
      }
      else 
      {
        if (config.debug) console.log("APP: Sign out success");
        account.status = false;
        account.name = '';
      }
    }
  
  }
  else
  {
    if (config.debug) console.log("APP: No active session. Please log in.");
    account.name = '';
    account.status = false;
  }

  if (error)
  {
    if (config.debug) console.log(error);
  }
}

// Fixing error due to hash routing and supabase sign up process
//src uses to help with fix: https://github.com/supabase/auth-js/issues/455

const parseURL = async () => 
{
    const hash = window.location.hash.substring(1);
    const decodedHash = decodeURIComponent(hash); 
    const params = new URLSearchParams(decodedHash.replace('/', '?'));
  
    const accessToken = params.get('access_token');
    const refreshToken = params.get('refresh_token');
    const type = params.get('type');
  
    console.log('Access Token:', accessToken);
    console.log('Refresh Token:', refreshToken);
    console.log('Type:', type);

    if (accessToken && refreshToken) 
    {
      const { data: { session }, error } = await supabase.auth.setSession({
        access_token: accessToken,
        refresh_token: refreshToken
      });

    if (session) {
      console.log('Session set successfully:', session);
      console.log('Session:', session);
      processSession(session, error);
      router.push("/platform/dashboard");
    } 
    else 
    {
      console.error('Error setting session:', error);
    }
  
  }

}
  


</script>

<style>
html 
{
  font-family: 'Roboto', sans-serif;
  margin: 0;
  padding: 0;
  height: 100%;
}

#spacer 
{
  height: 3em;
}

body 
{
  margin: 0;
  padding: 0;
  height: 100%;
}

#app
{
  height: 100%;
}

.appContainer
{
  height: 100%;
  display: flex;
  flex-direction: column;
}

.contentContainer
{
  flex: 1;
  height: 100%;
  overflow: hidden;
  background-color: #050a44;
}

.contentContainer.overflowAllowed
{
  overflow: auto;
}
</style>
