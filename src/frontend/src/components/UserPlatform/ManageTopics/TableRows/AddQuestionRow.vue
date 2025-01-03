<template>
    <tr>
        <td>  </td>
        <td class="inputField"> <input v-model="fields.question"/> <p v-if="validation.fillError" class="error"> No empty fields </p></td>
        <td class="inputField"> <input v-model="fields.answer"/> <p v-if="validation.fillError" class="error"> No empty fields </p></td>
        <td> <button class="editButton" @click="submitQuestion"> Submit </button> </td>
        
    </tr>
</template>

<script setup>
import { reactive, defineProps, defineEmits } from "vue";

const props = defineProps({
    topicID: {
        type: Number,
        required: true
    }
});

const fields = reactive({
    question: '',
    answer: ''
});

const validation = reactive({
    fillError: false
});

const emit = defineEmits(['updateQuestions']);

const submitQuestion = async () => 
{
    if (fields.question.length < 1 || fields.answer.length < 1)
    {
        validation.fillError = true;
        return;
    }
    else
    {
        validation.fillError = false;
    }

    const dbResponse = await fetch(`/api/fullQuestion/post/${fields.question}/${fields.answer}/${props.topicID}`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        }
    });

    const res = await dbResponse.text();

    console.log(res, "- submitQuestion response");
    emit('updateQuestions');
} 

</script>

<style scoped>
.error
{
    color: red;
}

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