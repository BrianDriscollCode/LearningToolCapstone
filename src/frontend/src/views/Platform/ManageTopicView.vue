<template>
    <span class="dashBoardLink" @click="goToLearningMap"> back to learning map... </span>
    <section class="topicContainer">
        <TopicInfoComponent :topic="topicInfo.data" />
        <hr />
        <ReferenceMaterialsComponent />
        <hr />
        <ReferenceQuestionsComponent />
    </section>

</template>

<script setup>
import TopicInfoComponent from '@/components/UserPlatform/ManageTopics/TopicInfoComponent.vue';
import ReferenceMaterialsComponent from '@/components/UserPlatform/ManageTopics/ReferenceMaterialsComponent.vue';
import ReferenceQuestionsComponent from '@/components/UserPlatform/ManageTopics/ReferenceQuestionsComponent.vue';
import { defineProps, onMounted, reactive } from 'vue';
import { useRouter } from 'vue-router';

const route = defineProps({
    topicID: {
        type: Number,
        required: true
    }
})

const topicInfo = reactive({
    data: {}
})

const router = useRouter();

const getTopic = async (topicID) =>
{
    try 
    {
        const dbResponse = await fetch(`/api/topics/get/${topicID}`);
        const data = await dbResponse.json();

        topicInfo.data = data;
        console.log(data);
        console.log("topicInfo.data = " + topicInfo.data);
    }
    catch (error) 
    {
        console.error(error);
    }    
}

onMounted(() => {
    getTopic(route.topicID);
})

const goToLearningMap = () => 
{
    router.push("/platform/learningMap");
}

</script>

<style scoped>

.topicContainer 
{
    background-color: aqua;
    margin-left: auto;
    margin-right: auto;
    width: 80%;
}

</style>