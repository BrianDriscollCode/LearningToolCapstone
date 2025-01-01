<template>
    <h3 id="referenceTitle"> Reference Materials </h3>
    <div class="tableContainer">
        <button @click="toggleAddReference" id="addReferenceButton"> Add Reference </button>
        <table class="referenceTable">
        <thead>
            <tr>
                <th> Name </th>
                <th> Location </th>
                <th> Actions </th>
            </tr>
        </thead>
        <tbody v-for="item in referenceMaterial.material" :key="item.id">
            <ReferenceRow 
                :isEdit="addReference.isEdit" 
                :referenceMaterial="item"
                :updateTable="getReferenceMaterial"
                @updateTable="getReferenceMaterial"
            />
        </tbody>
        <AddReferenceRow 
                v-if="addReference.isTrue"
                :topicID="props.topicID"
                :updateTable="getReferenceMaterial"
                @updateTable="getReferenceMaterial"
            />
    </table>
    </div>
    
</template>

<script setup>
import AddReferenceRow from './TableRows/AddReferenceRow.vue';
import ReferenceRow from './TableRows/ReferenceRow.vue';
import { reactive, defineProps, watch } from "vue";

// inherited from ManageTopicView to pass to AddReferenceRow
const props = defineProps({
    topicID: {
        type: Number,
        required: true
    }
});

const addReference = reactive({
    isTrue: false,
    isEdit: false
})

const referenceMaterial = reactive({
    material: []
})

const toggleAddReference = () =>
{
    addReference.isTrue = !addReference.isTrue;
}


const getReferenceMaterial = async () =>
{
    const dbResponse = await fetch(`/api/referenceMaterial/get/${props.topicID}`);

    const res = await dbResponse.json();

    const result = res.sort((a, b) => a.referenceID - b.referenceID);

    referenceMaterial.material = [...result];

    addReference.isTrue = false;
    console.log("reference material updated");
    console.log(referenceMaterial.material);
}

getReferenceMaterial();

watch(() => referenceMaterial.material, (newVal) => {
    console.log("Updated referenceMaterial.material:", newVal);
});
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

.tableWrapper
{
text-align: center;
}

.referenceInput
{
    width: 80%;
}
</style>