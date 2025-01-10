<template>
  <UserDataViewer />
  <SubjectDataViewer />
  <TopicDataViewer />
  <StudySessionsDataViewer />
  <QuestionDataViewer />
  <AnswerDataViewer />
</template>

<script setup>
  import UserDataViewer from "@/components/DataViewers/UserDataViewer.vue"
  import SubjectDataViewer from "@/components/DataViewers/SubjectDataViewer.vue";
  import TopicDataViewer from "@/components/DataViewers/TopicDataViewer.vue";
  import StudySessionsDataViewer from "@/components/DataViewers/StudySessionsDataViewer.vue";
  import QuestionDataViewer from "@/components/DataViewers/QuestionDataViewer.vue";
  import AnswerDataViewer from "@/components/DataViewers/AnswerDataViewer.vue";
</script>

<style>
html 
{
  margin: 0;
  padding: 0;
}

body 
{
  margin: 0;
  padding: 0;
}

</style>
  