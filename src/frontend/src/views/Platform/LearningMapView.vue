<template>
    <div>
        <span class="dashBoardLink" @click="goToDashboard"> dashboard </span>
        <span> / schedule </span> 
        <h1> Learning Map </h1>

        <table v-for="(session, index) in learningMapInfo.data" :key="index">
            <h2> {{ session.subject.name }}</h2>
            <tr>
                <th> Topics </th>
                <th> Competency </th>
                <th> Sessions </th>
            </tr>
            <tr>
                <td> {{ session.numberOfStudySessions.key.name}} </td>
                <td> {{ session.numberOfStudySessions.key.competency }} </td>
                <td> {{ session.numberOfStudySessions.value }} </td>
            </tr>
        </table>
        <LearningMapSubjectComponent />
    </div>
</template>

<script setup>
import { reactive } from 'vue';
import { useAccountStore } from '@/stores/account';
import LearningMapSubjectComponent from '@/components/UserPlatform/LearningMapSubjectComponent.vue';
import { useRouter } from 'vue-router';

const account = useAccountStore();
const learningMapInfo = reactive({
    data: []
});
const router = useRouter();

const getLearningMapInfo = async () =>
{
    const uuid = account.uuid;
    console.log(uuid);
    const dbResponse = await fetch(`/api/studySessions/learningMapInfo/${uuid}`);
    const data = await dbResponse.json();

    learningMapInfo.data = data;

    console.log(learningMapInfo.data);
} 

getLearningMapInfo();

const goToDashboard = () =>
{
    router.push("/platform/dashboard");
}

</script>

<style>
.dashBoardLink 
{
    color: rgb(86, 133, 221);
}

.dashBoardLink:hover
{
    cursor: pointer;
    color: rgb(245, 159, 1);
}
</style>