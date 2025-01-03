<template>
    <tr>
        <td class="tableWrapper"> <input class="referenceInput" v-model="reference.name" /> <p v-if="validation.fillError" class="error"> Cannot be empty </p> </td>
        <td class="tableWrapper"> <input class="referenceInput" v-model="reference.location" /> <p v-if="validation.fillError" class="error"> Cannot be empty </p> </td>
        <td id="submitBox"> <button id="submitButton" @click="submitReference"> submit </button> </td>
    </tr>

</template>

<script setup>
import { reactive, defineProps, defineEmits } from 'vue';

const reference = reactive({
    name: '',
    location: ''
})

const validation = reactive({
    fillError: false
})

const props = defineProps({
    topicID: {
        type: Number,
        required: true
    }
});

const emit = defineEmits('updateTable');

const submitReference = async () => 
{
    if (reference.name.length < 1 || reference.location.length < 1)
    {
        validation.fillError = true;
        return;
    }
    else
    {
        validation.fillError = false;
    }

    const dbResponse = await fetch("/api/referenceMaterial/post", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: reference.name,
            location: reference.location,
            topicID : props.topicID
        })
    });

    const res = await dbResponse.text();
    emit('updateTable');
    console.log(res);
}

</script>

<style scoped>
.error
{
    color: red;
}

#submitBox 
{
    border: none;
}

#submitButton
{
    width: 100%;
    padding: 1em;
}

.tableWrapper
{
text-align: center;
}

.referenceInput
{
    width: 80%;
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