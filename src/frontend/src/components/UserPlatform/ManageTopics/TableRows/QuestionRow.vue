<template>
    <tr>
        <td> {{ props.index + 1 }} </td>
        <td class="inputField" v-if="!state.edit"> {{ props.question.key.question }} </td>
        <td class="inputField" v-else> <input v-model="fields.question"/> </td>
        <td class="inputField" v-if="!state.edit"> {{ props.question.value.answer }} </td>
        <td class="inputField" v-else> <input v-model="fields.answer"/> </td>
        <td v-if="!state.edit"> <button class="editButton" @click="toggleEdit"> Edit </button> </td>
        <td v-else> <button class="editButton" @click="toggleEdit"> Cancel </button> </td>
        <td v-if="!state.edit"> <button class="editButton"> Delete </button> </td>
        <td v-else> 
            <button class="editButton"> Submit </button> 
            <button class="editButton"> AI Generate </button> 
        </td>
    </tr>
</template>

<script setup>
import { defineProps, reactive } from "vue";

const props = defineProps({
    question: {
        type: Object,
        required: true
    },
    index: {
        type: Number,
        required: true
    }
})

const state = reactive({
    edit: false
})

const fields = reactive({
    question: props.question.key.question,
    answer: props.question.value.answer
})

const toggleEdit = () =>
{
    state.edit = !state.edit;
}

</script>

<style scoped>


input 
{
    width: 100%;
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

.editButton
{
    height: 100%;
    width: 100%;
}

</style>