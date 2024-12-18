<template>
  <div class="appContainer">
    <NavBar />

    <div :class="['contentContainer', { 'overflowAllowed': overflowPage }]">
      <RouterView />
    </div>

  </div>
</template>

<script setup>
import NavBar from "@/components/DebugNav.vue";
import { computed } from "vue";
import { RouterView, useRoute } from 'vue-router';
import { supabase } from "./clients/supabase";
import { useAccountStore } from "./stores/account";
import { config } from "@/config"

const route = useRoute();
const account = useAccountStore();

// define which container styling to use depending on route (overflow hidden or not)
const overflowPage = computed(() => 
  route.name === "userHome" || 
  route.name === "dataPage")


// Initialize supabase auth and connect with user account in database
const initSession = async () => 
{
  const { data: { session }, error } = await supabase.auth.getSession();
  
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

initSession();

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
}

.contentContainer.overflowAllowed
{
  overflow: auto;
}
</style>
