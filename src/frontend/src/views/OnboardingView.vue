<template> 
    <WelcomeForm :class="[showForms.welcome ? welcomeFormShow : welcomeFormHidden]"/>
    <SubjectForm :class="[showForms.subject ? subjectFormShow: subjectFormHidden]"/>
    <TopicForm :class="[showForms.topic ? topicForm : topicFormHidden]" />
</template>

<script setup>
import WelcomeForm from "@/components/UserPlatform/OnboardingProcess/WelcomeForm.vue";
import SubjectForm from "@/components/UserPlatform/OnboardingProcess/SubjectForm.vue";
import TopicForm from "@/components/UserPlatform/OnboardingProcess/TopicForm.vue";

import { reactive } from "vue";

let showForms = reactive({
    welcome: true,
    subject: false,
    topic: false,
})

</script>

<style scoped>


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