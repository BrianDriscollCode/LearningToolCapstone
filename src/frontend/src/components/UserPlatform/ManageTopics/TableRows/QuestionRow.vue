<template>
    <tr>
        <td> {{ props.index + 1 }} </td>
        <td class="inputField" v-if="state.edit == aiGenerationState.OFFLINE || state.edit == aiGenerationState.FINISHED"> {{ props.question.key.question }} </td>
        <td class="inputField" v-else-if="state.edit == aiGenerationState.LOADING"> Loading... </td>
        <td class="inputField" v-else-if="state.edit == aiGenerationState.INPUT">
            <textarea v-model="fields.question" class="textArea"> </textarea> 
        </td>
        <td class="inputField" v-if="state.edit == aiGenerationState.OFFLINE || state.edit == aiGenerationState.FINISHED"> {{ props.question.value.answer }} </td>
        <td class="inputField" v-else-if="state.edit == aiGenerationState.LOADING"> Loading... </td>
        <td class="inputField" v-else-if="state.edit == aiGenerationState.INPUT">
            <textarea v-model="fields.answer" class="textArea"> </textarea> 
        </td>
        <td v-if="!state.edit"> <button class="editButton" @click="toggleEdit"> Edit </button> </td>
        <td v-else> 
            <button class="editButton" @click="toggleEdit"> Cancel </button> 
        </td>
        <td v-if="!state.edit"> 
            <button class="editButton" @click="deleteRow"> Delete </button> 
        </td>
        <td v-else> 
            <button class="editButton" @click="editRow"> Submit </button> 
            <button class="editButton" @click="getAIResponse"> Auto Generate </button> 
        </td>
    </tr>
</template>

<script setup>
import { defineProps, reactive, defineEmits } from "vue";

const props = defineProps({
    question: {
        type: Object,
        required: true
    },
    index: {
        type: Number,
        required: true
    },
    allQuestions: {
        type: Array,
        required: true
    },
    title: {
        type: String,
        required: true
    }
});

// handling the fetch process presentation to user
const aiGenerationState = {
    OFFLINE: 0,
    INPUT: 1,
    LOADING: 2,
    FINISHED:3
}

const emits = defineEmits(['updateQuestions'])

const state = reactive({
    edit: aiGenerationState.OFFLINE
})

const fields = reactive({
    question: props.question.key.question,
    answer: props.question.value.answer,
    history: []
})

// updating question history to generate unique responses
const updateInitialHistory = () =>
{
    for (let questionKey in props.allQuestions)
    {
        
        for (let key in props.allQuestions[questionKey])
        {
            fields.history.push({question: props.allQuestions[questionKey][key].key.question, answer: props.allQuestions[questionKey][key].value.answer});
        }

    }
}

updateInitialHistory();

const toggleEdit = () =>
{
    state.edit = !state.edit;
}

const editRow = async () =>
{
    const answer = encodeURIComponent(fields.answer);
    const question = encodeURIComponent(fields.question);

    const dbResponse = await fetch(`/api/fullQuestion/edit/${props.question.key.questionID}/${question}/${props.question.value.answerID}/${answer}`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        }
    });

    const res = await dbResponse.text();
    console.log(res);
    emits("updateQuestions");
    state.edit = aiGenerationState.FINISHED;
    toggleEdit();
}

const deleteRow = async () =>
{
    const dbResponse = await fetch(`/api/fullQuestion/delete/${props.question.key.questionID}/${props.question.value.answerID}`, {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        }
    });

    const res = await dbResponse.text();
    emits("updateQuestions");
    console.log(res);
}

const getAIResponse = async () => 
{
    try {
        state.edit = aiGenerationState.LOADING;
        const question = `Please give me a random question about ${props.title}, 15-25 words long. Past responses: ${fields.history.map(item => item.question)}`
        
        console.log(question);
        const encodedText = encodeURIComponent(question);

        const dbResponse = await fetch(`/api/ai/chat?prompt=${encodedText}`);

        if (!dbResponse.ok) {
            throw new Error(dbResponse.statusText);
        }

        const res = await dbResponse.text(); 

        console.log("Question: " + res);

        if (res)
        {
            getAIAnswer(res);
        }

    } 
    catch (error) 
    {
        console.error(error);
        state.edit = aiGenerationState.OFFLINE;
    }
}

const getAIAnswer = async (question) =>
{
    try
    {
        const answer = "Please give me an answer to this question, 15-25 words long. The Question: " + question;

        const encodedAnswer = encodeURIComponent(answer);

        const dbResponse = await fetch(`/api/ai/chat?prompt=${encodedAnswer}`);

        if (!dbResponse.ok) {
            throw new Error(dbResponse.statusText);
        }
        const resAnswer = await dbResponse.text();

        console.log("Answer: " + resAnswer);

        if (resAnswer)
        {
            fields.question = question;
            fields.answer = resAnswer;
            state.edit = aiGenerationState.INPUT;

            fields.history.push({question: fields.question, answer: fields.answer})
        }
    }
    catch (error)
    {
        console.error(error);
        state.edit = aiGenerationState.OFFLINE;
    }
    
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

.textArea
{
    width: 100%;
    height: 100px;
}

</style>