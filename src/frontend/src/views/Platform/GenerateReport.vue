<template>
    <section id="printSection">
        <div class="linkContainer">
            <span class="dashBoardLink" @click="goToDashboard"> back to Dashboard... </span>
        </div>
        <h1> Title: User Topics and Sessions Report </h1>
        <h2> User: {{ account.name }}</h2>
        <h2> Timestamp: {{ data.timeStamp }}</h2>
        <h2> Time: {{  data.date }}</h2>
        <Table>
            <thead>
                <tr>
                    <th> Topic </th>
                    <th> Subject </th>
                    <th> Scheduled Sessions </th>
                    <th> Competency </th>
                    <th> Learning Status </th>
                </tr>
            </thead>
            <tbody v-for="(session, index) in learningMapInfo.data" :key="index">
                <tr v-for="(topic, index) in session.numberOfStudySessions" :key="index">
                    <td> {{ topic.key.name }} </td>
                    <td> {{ session.subject.name }} </td>
                    <td> {{ topic.value }}</td>
                    <td> {{ topic.key.competency }} </td>
                    <td> {{  topic.key.learningStatus }} </td>
                </tr>
            </tbody>
        </Table>
        <div id="buttonContainer">
            <button @click="generateReport" id="generateButton"> Generate Report </button>
        </div>
    </section>
</template>

<script setup>
import { reactive } from 'vue';
import { useRouter } from "vue-router"
import { useAccountStore } from '@/stores/account';

const data = reactive({
    timeStamp: Date,
    date: Date
})

const router = useRouter();
const account = useAccountStore();

const learningMapInfo = reactive({
    data: []
})

const goToDashboard = () =>
{
    router.push("/platform/dashboard");
}

const getDates = () =>
{
    data.timeStamp = new Date().toISOString();
    data.date = new Date().toLocaleTimeString();
}

getDates();

const generateReport = () =>
{
    window.print();
}

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
} 

getLearningMapInfo();

</script>

<style scoped>
h1
{
    text-align: center;
}

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


@media print 
{
    #printSection {
        position: absolute;
        z-index: 10;
        height: 100%;
        width: 100%;
    }

    button {
        display: none;
    }

    .linkContainer
    {
        display: none;
    }
}


table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    table-layout:  fixed;
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

#buttonContainer
{
    text-align: center;
}

#generateButton
{
    width: 150px;
    height: 50px;
}

</style>