<template>
    <div class="userHomeContainer">
        <Loader v-show="state.loading"/>
        <NextStudySession v-show="!state.loading"/>

        <div class="otherOptionsContainer" v-show="!state.loading">
            <button @click="goToSchedule"> Schedule </button>
            <button @click="goToLearningMap"> Learning Map </button>
            <DashboardStatistics />
        </div>
    </div>
</template>

<script setup>
import NextStudySession from '@/components/UserPlatform/NextStudySession.vue';
import DashboardStatistics from '@/components/UserPlatform/DashboardStatistics.vue';
import Loader from "@/components/Widgets/LoaderAnim.vue"
import { supabase } from '@/clients/supabase';
import { useRouter } from 'vue-router'; 
import { reactive } from "vue"
import { config } from "@/config"
import { useAccountStore } from "@/stores/account";

const state = reactive({
    loading: true
});

const router = useRouter();
const account = useAccountStore();

const goToSchedule = () =>
{
    router.push("/platform/schedule");
}

const goToLearningMap = () =>
{
    router.push("/platform/learningMap");
}

// generate the study session data during onboarding
const generateData = async (uuid) => 
{
    try 
    {
        const dbResponse = await fetch(`/api/studySessions/generate?uuid=${uuid}`);

        if (!dbResponse.ok)
        {
            throw new Error(`Status: ${dbResponse.status}`)
        }

        const dataResponse = await dbResponse.text();
        if (config.debug) console.log("DASHBOARD VIEW: Data Response - " + dataResponse);
    }   
    catch (error)
    {
        if (config.debug) console.error("DASHBOARD VIEW: Error generating data - " + error);
    }
}

// Check onboarding and initialDataGenerated
// If onboarding == false, send to onboarding process
// If initialDataGenerated == false, generate data.
const checkOnboarding = async () =>
{

    const sessionResponse = await supabase.auth.getSession();
    let uuid = sessionResponse.data.session.user.id;
    
    if (config.debug) console.log("DASHBOARD VIEW: Uuid -", uuid);
    console.log("DASHBOARD VIEW: Store onboarding: " + account.onboardingFinished);
    console.log("DASHBOARD VIEW: Store initial data: " + account.initialDataGenerated);

    // Check account store to prevent unneccessary DB calls if possible
    if (account.onboardingFinished == true && account.initialDataGenerated == true)
    {
        if (config.debug) console.log("DASHBOARD VIEW: Store shows account is onboarded");
        state.loading = false;
        return;
    }

    // See if user has gone through onboarding process
    try 
    {
        const dbResponse = await fetch(`/api/users/by-uuid/${uuid}`);
        const onboardingResponse = await dbResponse.json();
        
        if (config.debug) console.log("DASHBOARD VIEW: Onboarding status -", onboardingResponse.onboardingFinished);
        
        if (!onboardingResponse.onboardingFinished)
        {
            // Send to onboarding process
            router.push("/platform/onboarding");
        }
        else
        {
            // Generate Data
            if (!onboardingResponse.initialDataGenerated)
            {  
                if (config.debug)
                {
                    console.log("DASHBOARD VIEW: need to generate data");
                    console.log("intialDataGenerated: ", onboardingResponse.initialDataGenerated);
                    console.log("Response: ", onboardingResponse);
                }
              
                generateData(uuid);
            }
            else
            {
                if (config.debug)
                {
                    console.log("DASHBOARD VIEW: data has already been generated");
                    console.log("intialDataGenerated: ", onboardingResponse.initialDataGenerated);
                }
            }

            state.loading = false;
            initSession();
        }
    }
    catch (error)
    {
        if (config.debug) console.error("DASHBOARD VIEW: " + error);
    }

}

checkOnboarding();

// Initialization just incase APP start does not initialize
const initSession = async () => 
{
  const { data: { session }, error } = await supabase.auth.getSession();
  
  if (session)
  {
    if (config.debug) console.log("DASHBOARDVIEW: Session is active - ", session);
    account.status = true;
    let uuid = session.user.id; 
    try
    {
      const response = await fetch(`/api/users/by-uuid/${uuid}`);
      const userData = await response.json();
      
      if (config.debug) console.log("DASHBOARDVIEW: User data - ", userData);
      
      account.name = userData.name;
      account.uuid = uuid;
      account.onboardingFinished = userData.onboardingFinished;
      account.initialDataGenerated = userData.initialDataGenerated;
    }
    catch (tryError)
    {
      if (config.debug) console.log("DASHBOARDVIEW: " + tryError);
      const { error } = await supabase.auth.signOut();

      if (error)
      {
        if (config.debug) console.log(error);
      }
      else 
      {
        if (config.debug) console.log("DASHBOARDVIEW: Sign out success");
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

</script>

<style scoped>
.userHomeContainer
{
    padding-top: 2em;
    height: 100%;
    background-color: rgb(82, 74, 65);
    display: flex;
    align-items: center;
    flex-direction: column;
   
}

button {
    background-color: rgb(230, 230, 230);
    padding: 1em 2em;
    margin: 5px;
    border: none;
    cursor: pointer;
    border-radius: 4px;
    font-size: 14px;
    transition: background-color 0.3s ease;
    margin-bottom: 1em;
}

button:hover
{
    background-color: rgb(123, 255, 255);
}

.otherOptionsContainer
{
    margin-top: 1em;
    display: flex;
    justify-content: center;
    flex-direction: column;
}


</style>