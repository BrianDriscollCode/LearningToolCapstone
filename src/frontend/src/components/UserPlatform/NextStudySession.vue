<template>
    <section class="studySessionContainer">
        <h2> Next Study Session </h2>
        <p v-if="filteredSessions.length <= 0 && !studySessionList.loaded"> Loading... </p> 
        <p v-else-if="filteredSessions.length <= 0 && studySessionList.loaded"> No sessions scheduled today... </p>
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
import { reactive, computed, watchEffect, defineExpose } from "vue";
import { useRouter } from 'vue-router';

const account = useAccountStore();
const router = useRouter();

const studySessionList = reactive({
    sessions: [],
    loaded: false
});

const getStudySessions = async () =>
{
    console.log("NextStudySession::getStudySessions::account.uuid:" + account.uuid);
    const uuid = account.uuid;
    const dbResponse = await fetch(`/api/studySessions/get/${uuid}`);
    const studySessions = await dbResponse.json();

    studySessionList.sessions.splice(0, studySessionList.sessions.length, ...studySessions);


    // Set up reoccuring calls
    const maxCalls = 3;
    let currentCalls = 0;
    const timeout = (time) => new Promise(resolve => setTimeout(resolve, time));

    while (studySessionList.sessions.length <= 0 && currentCalls < maxCalls)
    {
        await timeout(2000);
        const dbResponse = await fetch(`/api/studySessions/get/${uuid}`);
        const studySessions = await dbResponse.json();
        studySessionList.sessions.splice(0, studySessionList.sessions.length, ...studySessions);

        currentCalls += 1;
    }   

    studySessionList.loaded = true;

    console.log("NextStudySession:getStudySessions:UUID-" + uuid);
    console.log("NextStudySession:getStudySessions:sessions" + studySessionList.sessions);
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
        let start = 0;

        return studySessionList.sessions.filter(session => {
            const sessionDate = new Date(session.date).toLocaleDateString('en-CA');
            console.log(start + ": " + sessionDate)
            console.log(start + ": " + todayDate)
            start++;
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


watchEffect(() => {
  console.log('Re-evaluating filteredSessions:', filteredSessions.value);
});



const goToStartSession = () =>
{
    router.push("/platform/study");
}

defineExpose({
    getStudySessions
});
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