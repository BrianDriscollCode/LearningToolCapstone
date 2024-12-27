<template>
    
    <h2> Topic: {{ id.name }}</h2>
    <div v-if="!rescheduleSuccess">
        <span> Pick a date: </span>
        <select v-model="selectedDate">
            <option v-for="day in getDaysInMonth()" :key="day" :value="day"> {{ day }} </option>
        </select>
        <p> {{ selectedDate }}</p>
        <div>
            <button @click="reschedule"> Reschedule </button>
            <button> Delete </button>
        </div>
    </div>
    <div v-if="rescheduleSuccess">
        <h3> Topic Successfully Rescheduled </h3>
        <button @click="goToDashboard"> Back to dashboard </button>
    </div>

</template>


<script setup>
import { defineProps, ref } from 'vue';
import { useRouter } from 'vue-router';

const id = defineProps({
    studySessionID: Number,
    name: String
})


const selectedDate = ref('');
const rescheduleSuccess = ref(false);

const router = useRouter();


const reschedule = async () =>
{
    const splitDate = selectedDate.value.split('/');
    const formattedDate = `${splitDate[2]}-${splitDate[0]}-${splitDate[1]}`;

    console.log(id.studySessionID + " -studysessionID");
    console.log(selectedDate.value + " -selectedDate");
    const dbResponse = await fetch(`/api/studySessions/reschedule/${id.studySessionID}/${formattedDate}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    });

    const res = await dbResponse.text();
    
    if (res)
    {
        rescheduleSuccess.value = true;
    }   
    console.log(res);
    console.log(rescheduleSuccess);
}


const getDaysInMonth = () =>
{
    const today = new Date();

    const daysArray = [];

    for (let i = 0; i <= 7; i++)
    {
        const date = new Date(today);
        date.setDate(today.getDate() + i);
        daysArray.push(date.toLocaleDateString('en-US'));
    } 

    return daysArray;
    
}

const goToDashboard = () => 
{
    router.push('/platform/dashboard');
}
    

</script>