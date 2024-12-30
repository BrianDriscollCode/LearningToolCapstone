<template>
    <p> Reference Materials </p>
    <button @click="toggleAddReference"> Add Topic </button>
    <table>
        <thead>
            <th> Name </th>
            <th> Location </th>
        </thead>
        <tbody>
            <AddReferenceComponent  v-if="addReference.isTrue"/>
        </tbody>
    </table>
</template>

<script setup>
import AddReferenceComponent from './AddReferenceComponent.vue';
import { reactive } from "vue";


const addReference = reactive({
    isTrue: false
})

const toggleAddReference = () =>
{
    addReference.isTrue = !addReference.isTrue;
}

//fetch request for reference materials

//fetch request for submitting reference materials


</script>