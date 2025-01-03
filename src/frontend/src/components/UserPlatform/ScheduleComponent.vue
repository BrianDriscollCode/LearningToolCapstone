<template>
    <div class="scheduleContainer">
        <h2> {{ monthName }} </h2>
        <table>
            <thead>
            <tr>
                <th> Date </th>
                <th> Study Sessions </th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="day in daysInMonth" :key="day">
                <td> {{ day }} </td>
                <td>
                    <span v-for="(session, index) in getSessionsForDate(day)" :key="session"> 
                        {{ session.topicID.name }}
                        <span v-if="index < getSessionsForDate(day).length - 1">, </span>
                    </span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { reactive, watchEffect } from "vue";
import { useAccountStore } from "@/stores/account";

const account = useAccountStore();

const studySessionList = reactive({
    sessions: []
})

const getStudySessions = async () =>
{
    const uuid = account.uuid;
    const dbResponse = await fetch(`/api/studySessions/get/${uuid}`);
    const studySessions = await dbResponse.json();

    studySessionList.sessions = studySessions;

    studySessions.forEach(session => {
        console.log(session.studySessionID, + "- " + session.date);
    })
}

const getSessionsForDate = (fieldDate) => {
    if (Array.isArray(studySessionList.sessions)) {
        return studySessionList.sessions.filter(session => {
            const createDateObject = new Date(session.date);
            // Adding a day because cannot get en-US to match the day! AGHHHHHH!!
            createDateObject.setDate(createDateObject.getDate() + 1);
            const convertedTypeDate = createDateObject.toLocaleDateString('en-US');
            console.log(convertedTypeDate + " - " + fieldDate);
            return convertedTypeDate === fieldDate;
        });
    }
    return [];
};

watchEffect(() => {
    if (account.uuid)
    {   
        getStudySessions();
    }
});


// Date logic
const currentDate = new Date(); 
const currentDay = currentDate.getDate();
const currentMonth = currentDate.getMonth(); 
const currentYear = currentDate.getFullYear();

const monthName = new Intl.DateTimeFormat('en-US', { month: 'long' }).format(currentDate)

const lastDayOfMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

const daysInMonth = [];

// storing all the days of the current month from today in array
for (let day = currentDay; day <= lastDayOfMonth; day++) {
    const formattedDay = currentMonth + 1 + "/" + day + "/" + currentYear;
    daysInMonth.push(formattedDay);
}

</script>

<style scoped>
h2
{
    color: white;
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
    background-color: #E56B6F;
    color: rgb(46, 46, 46);
}

tr:nth-child(even) {
    background-color: #ebe5d4;
}

tr:nth-child(odd) {
    background-color: #6eddd4;
}

.scheduleContainer
{
    width: 700px;
    margin-left: auto;
    margin-right: auto;
}
</style>