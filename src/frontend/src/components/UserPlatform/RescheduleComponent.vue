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
        <p v-if="validation.fillError" id="error"> Fill the date field </p>
    </div>
    <div v-if="rescheduleSuccess">
        <h3> Topic Successfully Rescheduled </h3>
        <button @click="goToDashboard"> Back to dashboard </button>
    </div>

</template>


<script setup>
import { defineProps, ref, reactive } from 'vue';
import { useRouter } from 'vue-router';

const id = defineProps({
    studySessionID: String,
    name: String
})

const validation = reactive({
    fillError: false
})


const selectedDate = ref('');
const rescheduleSuccess = ref(false);

const router = useRouter();


const reschedule = async () =>
{
    if (selectedDate.value.length < 1)
    {
        validation.fillError = true;
        return;
    }
    else
    {
        validation.fillError = false;
    }

    const splitDate = selectedDate.value.split('/');
    const year = splitDate[2];
    const month = splitDate[0].padStart(2, '0');
    const day = splitDate[1].padStart(2, '0');

    const formattedDate = `${year}-${month}-${day}`;
    console.log(formattedDate);

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

<style scoped>
#error
{
    color: red;
}

</style>