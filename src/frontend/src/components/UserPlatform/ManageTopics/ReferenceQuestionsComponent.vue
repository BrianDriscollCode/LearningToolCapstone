<template>
    <h3 id="referenceQuestionsTitle"> Reference Questions </h3>
    <div class="referenceQuestionsContainer">
        <button id="addQuestionButton" @click="toggleAddQuestion"> Add Question </button>
        <table class="questionTable">
                <thead>
                    <th> # </th>
                    <th> Question </th>
                    <th> Answer </th>
                    <th> Action </th>
                </thead>
                <tbody>
                    <tr>
                        <td> 1</td>
                        <td class="inputField"> <input /> </td>
                        <td class="inputField"> <input /> </td>
                        <td> <button class="editButton"> Edit </button> </td>
                        <td> <button class="editButton"> AI Generate </button> </td>
                    </tr>
                    <AddQuestionRow v-if="state.addQuestion"/>
                </tbody>
        </table>
    </div>
    
</template>

<script setup>
import AddQuestionRow from './TableRows/AddQuestionRow.vue';
import { reactive } from 'vue';

const state = reactive({
    addQuestion: false
})

const toggleAddQuestion = () =>
{
    state.addQuestion = !state.addQuestion;
}

</script>

<style scoped>
input {
    width: 100%;
}

.inputField
{
    
}

#referenceQuestionsTitle
{
    text-align: center;
}

.referenceQuestionsContainer
{
    width: 90%;
    margin-left: auto;
    margin-right: auto;
    padding-bottom: 1em;
}

#addQuestionButton
{
    padding: 1em;
    margin-bottom: 1em;
}

.questionTable
{

    margin-bottom: 1em;
}

.editButton
{
    height: 100%;
    width: 100%;
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