<template>
    <h2> {{ id }} </h2>
    <div>
        <span> Pick a date: </span>
        <select>
            <option v-for="day in getDaysInMonth()" :key="day"> {{ day }} </option>
        </select>
        <div>
            <button> Reschedule </button>
            <button> Delete </button>
        </div>
    </div>

</template>


<script setup>
// import { defineProps } from "vue";

// defineProps({
//   id: {
//     type: Number
//     required: true
//   }
// });



// const topicInfo = reactive({
//     date: null
// })

const getDaysInMonth = () =>
{
    const today = new Date();

    const daysArray = [];

    for (let i = 0; i <= 7; i++)
    {
        const date = new Date(today);
        date.setDate(today.getDate() + i);
        daysArray.push(date);
    } 

    return daysArray;
    
}
    

</script>