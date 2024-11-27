<template>
    <div class="userHomeContainer">
        <NextStudySession />
    </div>
</template>

<script setup>
import NextStudySession from '@/components/UserPlatform/NextStudySession.vue';
import { supabase } from '@/clients/supabase';
import { useRouter } from 'vue-router'; 

const router = useRouter();

const checkOnboarding = async () =>
{
    const sessionResponse = await supabase.auth.getSession();
    let uuid = sessionResponse.data.session.user.id;
    
    console.log("DASHBOARD VIEW: Uuid -", uuid);
    
    const dbResponse = await fetch(`/api/users/by-uuid/${uuid}`);
    const onboardingResponse = await dbResponse.json();

    console.log("DASHBOARD VIEW Onboarding status -", onboardingResponse.onboardingFinished);

    if (!onboardingResponse.onboardingFinished)
    {
        router.push("/platform/onboarding");
    }
}
checkOnboarding();

</script>

<style scoped>
.userHomeContain
{
    height: 100%;
    background-color: rgb(77, 77, 77);
   
}

</style>