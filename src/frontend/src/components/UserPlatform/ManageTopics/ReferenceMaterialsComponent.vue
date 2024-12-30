<template>
    <h3 id="referenceTitle"> Reference Materials </h3>
    <div class="tableContainer">
        <button @click="toggleAddReference" id="addReferenceButton"> Add Reference </button>
        <table class="referenceTable">
        <thead>
            <tr>
                <th> Name </th>
                <th> Location </th>
                <th> Action </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="tableWrapper"> test </td>
                <td class="tableWrapper"> test </td>
                <td id="editBox"> <button id="editButton"> Edit </button> </td>
            </tr>
            <AddReferenceRow  v-if="addReference.isTrue"/>
        </tbody>
    </table>
    </div>
    
</template>

<script setup>
import AddReferenceRow from './TableRows/AddReferenceRow.vue';
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

<style scoped>
#referenceTitle
{
    text-align: center;
}

.tableContainer
{
    width: 90%;
    margin-left: auto;
    margin-right: auto;
}

#addReferenceButton
{
    padding: 1em;
    margin-bottom: 1em;
}

.referenceTable
{
    
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

#submitBox 
{
    border: none;
}

#editButton
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
</style>