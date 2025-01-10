<template> 
    <section class="onboardingContainer">
        <WelcomeForm
            :class="[showForms.welcome ? 'welcomeFormShow' : 'welcomeFormHidden']"
            @hideWelcomeComponent="hideWelcomeComponent"
        />
        <SubjectForm
            :class="[showForms.subject ? 'subjectFormShow':'subjectFormHidden']"
            @fillSubjects="fillSubjects"
        />
        <TopicForm
            :class="[showForms.topic ? 'topicForm' : 'topicFormHidden']"
            @fillTopics="fillTopics"
            :subject="databaseInfo.subject"
        />
    </section>
</template>

<script setup>
import WelcomeForm from "@/components/UserPlatform/OnboardingProcess/WelcomeForm.vue";
import SubjectForm from "@/components/UserPlatform/OnboardingProcess/SubjectForm.vue";
import TopicForm from "@/components/UserPlatform/OnboardingProcess/TopicForm.vue";
import { useAccountStore } from "@/stores/account";

import { reactive } from "vue";


import { useRouter } from "vue-router";

const router = useRouter();

const account = useAccountStore();

let databaseInfo = reactive({
    subject: '',
    topicOne: '',
    topicOneCompetency: 'beginner',
    topicTwo: '',
    topicTwoCompetency: 'beginner',
});

let showForms = reactive({
    welcome: true,
    subject: false,
    topic: false,
});

function hideWelcomeComponent()
{
    showForms.welcome = false;
    showForms.subject = true;
    showForms.topic = false;
}

function fillSubjects(subject)
{

    databaseInfo.subject = subject;

    showForms.welcome = false;
    showForms.subject = false;
    showForms.topic = true;
}

async function fillTopics(topics)
{
    databaseInfo.topicOne = topics.one;
    databaseInfo.topicOneCompetency = topics.oneCompetency.toUpperCase();
    databaseInfo.topicTwo = topics.two;
    databaseInfo.topicTwoCompetency = topics.twoCompetency.toUpperCase();

    // update database
    // return data to see that it is updated and onboarding is finished

    console.log(databaseInfo.subject);
    console.log(databaseInfo.topicOne);
    console.log(databaseInfo.topicOneCompetency);
    console.log(databaseInfo.topicTwo);
    console.log(databaseInfo.topicTwoCompetency);
    console.log(account.uuid);

    const response = await fetch("/api/onboarding/create", {
        method: "POST",
        headers:
        {
            "Content-Type" : "application/json",
        },
        body: JSON.stringify({
            uuid: account.uuid,
            subjectName: databaseInfo.subject,
            topicName1: databaseInfo.topicOne,
            competency1: databaseInfo.topicOneCompetency,
            topicName2: databaseInfo.topicTwo,
            competency2: databaseInfo.topicTwoCompetency
        })
    });

    if (!response.ok)
    {
        console.log("ONBOARDING VIEW: bad response - " + response);
    }
    else
    {
        console.log("ONBOARDING VIEW: successful onboarding response!");
        router.push("/platform/dashboard");
    }
}
</script>

<style scoped>

.onboardingContainer
{
    height: 100%;
    background-color: rgb(77, 77, 77);
    display: flex;
    align-items: center;
    justify-content: center;
}

.welcomeFormShow
{
    display: block;
}

.welcomeFormHidden
{
    display: none;
}

.subjectFormShow
{
    display: block;
}

.subjectFormHidden
{
    display: none;
}

.topicFormShow
{
    display: block;
}

.topicFormHidden
{
    display: none;
}



</style>