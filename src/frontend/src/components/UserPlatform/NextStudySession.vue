<template>
    <section class="studySessionContainer">
        <h2> Next Study Session </h2>
        <p v-if="filteredSessions.length <= 0"> Loading... </p> 
        <table v-else>
            <tr>
                <th> Topic </th>
                <th> Date </th>
                <th> Actions </th>
            </tr>
            <tr v-for="(session, index) in filteredSessions" :key="index">
                <td> {{ session.topicID.name }} </td>
                <td> {{ new Date(session.date).toLocaleDateString('en-ca') }} </td>
                <td> <button @click="goToStartSession"> Start </button> </td>
                <td> <button> Reschedule </button> </td>
                <td> <button> Delete </button> </td>
            </tr>
        </table>

    </section>
</template>

<script setup>
import { useAccountStore } from '@/stores/account';
import { reactive, computed, watchEffect } from "vue";
import { useRouter } from 'vue-router';

const account = useAccountStore();
const router = useRouter();

const studySessionList = reactive({
    sessions: []
});

const getStudySessions = async () =>
{
    const uuid = account.uuid;
    const dbResponse = await fetch(`/api/studySessions/get/${uuid}`);
    const studySessions = await dbResponse.json();

    studySessionList.sessions = studySessions;
}

const getCurrentDate = () => {
    const today = new Date();
    return today.toLocaleDateString('en-CA'); 
};

// Matching up the sessions that are for the current date
const filteredSessions = computed(() => {
    const todayDate = getCurrentDate();
    if (Array.isArray(studySessionList.sessions))
    {
        return studySessionList.sessions.filter(session => {
            const sessionDate = new Date(session.date).toLocaleDateString('en-CA');
            return sessionDate === todayDate;
        });
    }
    return [];
    
});

// Preventing the error from waiting for localStorage to update store
watchEffect(() => {
  if (account.uuid) {
    getStudySessions();
  }
});

const goToStartSession = () =>
{
    router.push("/platform/study");
}

</script>

<style scoped>
.studySessionContainer {
    background-color: #cefff4;
    padding: 2em;
    border-radius: 1em;
}

h2 {
    text-align: center;
    font-size: 1.8rem;
    margin-bottom: 1.5em;
    color: #333;
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

button {
    padding: 1em 2em;
    margin: 5px;
    border: none;
    cursor: pointer;
    border-radius: 4px;
    font-size: 14px;
    transition: background-color 0.3s ease;
}

button:hover
{
    background-color: rgb(94, 201, 201);
}


button.delete-btn {
    background-color: #f44336;
    color: white;
}

button.delete-btn:hover {
    background-color: #da190b;
}

p {
    text-align: center;
    font-size: 1.2rem;
    color: #666;
    margin-top: 20px;
}
</style>