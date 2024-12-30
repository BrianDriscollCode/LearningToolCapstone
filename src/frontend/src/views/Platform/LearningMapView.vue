<template>
    <div>
        <span class="dashBoardLink" @click="goToDashboard"> back to dashboard... </span>
        <h1> Learning Map </h1>

        <button @click="goToAddingSubject"> Add Subject </button>

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
                <td> <button @click="goToEditTopic(pair.key.topicID)"> Edit Topic... </button> </td>
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
    let retryAmount = 0;
    let maxRetries = 3;
    const cooldown = 1000;

    // Store updates via APP. If user refreshes, will cause this to take time to be updated, therefore 
    // using multiple attempts just incase a user hits refresh.
    while (retryAmount < maxRetries)
    {
        try
        {
            if (account.uuid)
            {
                const uuid = account.uuid;
                console.log(uuid);
                const dbResponse = await fetch(`/api/studySessions/learningMapInfo/${uuid}`);

                if (!dbResponse.ok)
                {
                    throw new Error("dbResponse error: " + dbResponse.status);
                }
                const data = await dbResponse.json();
                console.log("Learning Map data fetched successfully: ");
                learningMapInfo.data = data;
                return;
            }
            else
            {
                throw new Error("account not loaded");
            }
        }
        catch (error)
        {
            retryAmount++;
            console.log(error);

            if (retryAmount < maxRetries)
            {
                console.log("Retrying account store...");
                await new Promise(resolve => setTimeout(resolve, cooldown));
            }
        }
    
     
    }

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

const goToAddingSubject = () =>
{
    router.push(`/platform/addingSubject`);   
}

const goToEditTopic = (topicID) =>
{
    router.push(`/platform/manageTopic/${topicID}`);
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