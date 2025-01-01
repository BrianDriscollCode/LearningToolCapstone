<template>
    <h3 id="referenceQuestionsTitle"> Review Questions </h3>
    <div class="referenceQuestionsContainer">
        <button 
            id="addQuestionButton" 
            @click="toggleAddQuestion" v-if="!state.addQuestion"> 
                Add Question 
        </button>
        <button 
            id="addQuestionButton" 
            @click="toggleAddQuestion" v-else> 
                Cancel
        </button>
        <table class="questionTable">
                <thead>
                    <th class="numberField"> # </th>
                    <th> Question </th>
                    <th> Answer </th>
                    <th> Action </th>
                </thead>
                <tbody v-for="(item, index) in question.object" :key="index">
                    <QuestionRow 
                        :question="item" 
                        :index="index"
                        :allQuestions="question"
                        :title="props.topic.name"
                        :updateQuestions="getQuestions"
                        @updateQuestions="getQuestions"
                    />
                </tbody>
                <AddQuestionRow 
                    v-if="state.addQuestion"
                    :topicID="props.topicID"
                    :updateQuestions="getQuestions"
                    @updateQuestions="getQuestions"
                />
        </table>
    </div>
    
</template>

<script setup>
import QuestionRow from './TableRows/QuestionRow.vue';
import AddQuestionRow from './TableRows/AddQuestionRow.vue';
import { reactive, defineProps, watch } from 'vue';

const state = reactive({
    addQuestion: false
})

const question = reactive({
    object: []
})

const toggleAddQuestion = () =>
{
    state.addQuestion = !state.addQuestion;
}

const props = defineProps({
    topicID: {
        type: Number,
        required: true
    },
    topic: {
        type: Object,
        required: true
    }
})

const getQuestions = async () => 
{
    const dbResponse = await fetch(`/api/fullQuestion/get/${props.topicID}`);
    const res = await dbResponse.json();

    const result = res.sort((a, b) => a.key.questionID - b.key.questionID);

    question.object = [...result];
    state.addQuestion = false;
}

getQuestions();

watch(() => question.object, (newVal) => {
    console.log("Updated referenceMaterial.material:", newVal);
});

</script>

<style scoped>
input {
    width: 100%;
}

.numberField
{
    width: 10px;
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