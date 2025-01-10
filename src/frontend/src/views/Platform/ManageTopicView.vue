<template>
    <div>
        <div class="linkContainer">
            <span class="dashBoardLink" @click="goToLearningMap"> back to learning map... </span>
        </div>
        
        <section class="topicContainer">
            <TopicInfoComponent :topic="topicInfo.data" />
            <hr />
            <ReferenceMaterialsComponent :topicID="topicID" />
            <hr />
            <QuestionsComponent :topicID="topicID" :topic="topicInfo.data" />
        </section>
    </div>

</template>

<script setup>
import TopicInfoComponent from '@/components/UserPlatform/ManageTopics/TopicInfoComponent.vue';
import ReferenceMaterialsComponent from '@/components/UserPlatform/ManageTopics/ReferenceMaterialsComponent.vue';
import QuestionsComponent from '@/components/UserPlatform/ManageTopics/QuestionsComponent.vue';
import { onMounted, reactive } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const route = useRoute();

const topicID = Number(route.params.topicID);

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
    getTopic(topicID);
})

const goToLearningMap = () => 
{
    router.push("/platform/learningMap");
}

</script>

<style scoped>

.linkContainer
{
    padding: 1em;
}

.dashBoardLink
{
    color: rgb(86, 133, 221);
}

.dashBoardLink:hover
{
    cursor: pointer;
    color: rgb(245, 159, 1);
}

.topicContainer 
{
    background-color: #cefff4;
    margin-left: auto;
    margin-right: auto;
    width: 80%;
}

</style>