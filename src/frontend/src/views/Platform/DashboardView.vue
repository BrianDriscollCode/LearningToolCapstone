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

const state = reactive({
    loading: true
});

const router = useRouter();

const generateData = async (uuid, howManyDays) => 
{
    try 
    {
        const dbResponse = await fetch(`/api/studySessions/generate?uuid=${uuid}&howManyDays=${howManyDays}`);

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

const checkOnboarding = async () =>
{
    const sessionResponse = await supabase.auth.getSession();
    let uuid = sessionResponse.data.session.user.id;
    
    if (config.debug) console.log("DASHBOARD VIEW: Uuid -", uuid);
    
    // See if user has gone through onboarding process
    try 
    {
        const dbResponse = await fetch(`/api/users/by-uuid/${uuid}`);
        const onboardingResponse = await dbResponse.json();
        
        if (config.debug) console.log("DASHBOARD VIEW: Onboarding status -", onboardingResponse.onboardingFinished);
        
        if (!onboardingResponse.onboardingFinished)
        {
            router.push("/platform/onboarding");
        }
        else
        {
            state.loading = false;

            if (!onboardingResponse.intialDataGenerated)
            {  
                if (config.debug) console.log("DASHBOARD VIEW: NEED TO GENERATE DATA");
                let howManyDays = 31;
                generateData(uuid, howManyDays);
            }
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