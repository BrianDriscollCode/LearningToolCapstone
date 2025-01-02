<template>
    <tr> 
        <td> {{ props.index }} </td>
        <td> {{ props.session.topicID.name }} </td>
        <td> {{ props.session.date }} </td>
    </tr>
</template>

<script setup>
import { reactive, defineProps } from "vue";

const props = defineProps({
    input: {
        type: String,
        required: true
    },
    session: {
        type: Array,
        required: true
    },
    index:
    {
        type: Number,
        required: true
    }
});

const topic = reactive({
    searchTerm: props.input,
});


console.log(topic.searchTerm);

</script>

<style scoped>
#studySessionSearchContainer
{
    width: 70%;
    background-color: rgb(172, 199, 75);
    margin-left: auto;
    margin-right: auto;
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