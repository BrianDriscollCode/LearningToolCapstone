<template>
    <div>
        <span class="dashBoardLink" @click="goToDashboard"> dashboard </span>
        <span> / schedule </span> 
        <h1> Learning Map </h1>

        <button> Add Subject </button>

        <div v-for="(session, index) in learningMapInfo.data" :key="index">
            <table>
            <h2> {{ session.subject.name }}</h2>
            <tr>
                <th> Topics </th>
                <th> Competency </th>
                <th> Sessions </th>
                <th> Actions </th>
            </tr>
            <tr v-for="(pair, index) in session.numberOfStudySessions" :key="index">
                <td> {{ pair.key.name}} </td>
                <td> {{ pair.key.competency }} </td>
                <td> {{ pair.value }} </td>
                <td> <button> Edit Topic... </button> </td>
            </tr>
            <button @click="goToAddingTopic(session.subject.name, session.subject.subjectID)"> Add Topic </button>
            </table>
        </div>

    </div>
</template>

<script setup>
import { reactive } from 'vue';
import { useAccountStore } from '@/stores/account';
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

const goToAddingTopic = (subjectID, name) =>
{
    router.push(`/platform/addingTopic/${subjectID}/${name}`);
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

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

th, td {
    padding: 12px;
    text-align: left;
    border: 1px solid #dbd6c6;
}

th {
    background-color: #fab22b;
    color: rgb(46, 46, 46);
}

tr:nth-child(even) {
    background-color: #ebe5d4;
}

tr:nth-child(odd) {
    background-color: #86fff5;
}


</style>