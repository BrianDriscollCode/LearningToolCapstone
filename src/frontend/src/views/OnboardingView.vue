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

import { reactive } from "vue";

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

    console.log(databaseInfo.subject);
}

function fillTopics(topics)
{

    console.log(topics.one);
    console.log(topics.oneCompetency);
    console.log(topics.two);
    console.log(topics.twoCompetency);


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