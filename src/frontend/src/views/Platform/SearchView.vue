<template>
    <div class="linkContainer">
        <span class="dashBoardLink" @click="goToDashboard"> back to dashboard... </span>
    </div>
    <div id="searchContainer">
        <h1> Search </h1>
        <input v-model="search.input" id="inputSearch" /> 
        <button @click="startSearch" id="buttonSearch"> Search Study Topic </button>
    </div>

    <table id="searchTable">
        <thead>
            <tr>
                <th> # </th>
                <th> Name </th>
                <th> Date </th>
            </tr>
        </thead>
        <tbody div v-for="(session, index) in searchFilter" :key="index">
                <StudySessionsSearchRow
                    :input="search.input" 
                    :session="session"
                    :index="index"
                /> 
        </tbody>
    </table>
    

    
</template>

<script setup>
import { reactive, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useAccountStore } from '@/stores/account';
import StudySessionsSearchRow from '@/components/Search/StudySessionsSearchRow.vue';

const search = reactive({
    input: '',
    enteredInput: 'NONE',
    topicInfo: []
});

const account = useAccountStore();

const router = useRouter();

const goToDashboard = () =>
{
    router.push("/platform/dashboard");
}

const getStudySessionInfo = async () =>
{
    try
    {
        const dbResponse = await fetch(`/api/studySessions/get/${account.uuid}`);
        const res = await dbResponse.json();
        
        console.log("response: " + res);
        console.log("response");
        search.topicInfo = [...res];
    }
    catch (error)
    {
        console.log(error);
    }
}

getStudySessionInfo();

const startSearch = () =>
{
    search.enteredInput = search.input;
}

const searchFilter = computed(() => {

    const compareInput = search.enteredInput.toLowerCase();

    let filteredTopics =  search.topicInfo.filter(session => 
        session.topicID.name.toLowerCase().includes(compareInput)
    );

    return filteredTopics;

});
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

h1 
{
    padding: 0;
    margin: 0 0 1em 0;
}

#searchContainer
{
    background-color: aquamarine;
    width: 400px;
    text-align: center;
    padding: 1em;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 1em;
    border-radius: 1em;
}

#inputSearch
{
    height: 30px;
    margin-right: 1em;
}

#buttonSearch
{
    height: 30px;
}

#searchTable
{
    width: 50%;
    margin-left: auto;
    margin-right: auto;
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
</style>