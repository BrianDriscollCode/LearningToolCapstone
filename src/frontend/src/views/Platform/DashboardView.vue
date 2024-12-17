<template>
    <div class="userHomeContainer">
        <Loader v-show="state.loading"/>
        <NextStudySession v-show="!state.loading"/>
    </div>
</template>

<script setup>
import NextStudySession from '@/components/UserPlatform/NextStudySession.vue';
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
// If onboarding == false, send to onboaridng process
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
        }
    }
    catch (error)
    {
        if (config.debug) console.error("DASHBOARD VIEW: " + error);
    }

}

checkOnboarding();


</script>

<style scoped>
.userHomeContainer
{
    height: 100%;
    background-color: rgb(77, 77, 77);
    display: flex;
    justify-content: center;
    align-items: center;
   
}



</style>