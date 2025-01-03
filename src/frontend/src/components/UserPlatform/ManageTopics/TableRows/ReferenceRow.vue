<template>
    <tr>
        <td class="tableWrapper" v-if="!state.isEdit"> {{ fields.name }}</td>
        <td class="tableWrapper" v-else> <input v-model="fields.name"/> <p v-if="validation.fillError" class="error"> Cannot be empty </p> </td>
        
        
        <td class="tableWrapper" v-if="!state.isEdit"> {{ fields.location }}</td>
        <td class="tableWrapper" v-else> <input v-model="fields.location"/> <p v-if="validation.fillError" class="error"> Cannot be empty </p> </td>

        <td id="editBox" v-if="!state.isEdit"> 
            <button id="editButton" @click="toggleIsEdit()"> Edit </button> 
            <button id="editButton" @click="deleteReferenceMaterial"> Delete </button> 
        </td>
        <td id="editBox" v-else> 
            <button id="editButton" @click="toggleIsEdit()"> Cancel </button> 
            <button id="editButton" @click="submitReferenceEdit"> Submit </button> 
        </td>
    </tr>
</template>

<script setup>
import { reactive, defineProps, defineEmits, watchEffect} from 'vue';

const props = defineProps({
    referenceMaterial: {
        type: Object,
        require: true
    }
});

const emits = defineEmits(['updateTable']);

const state = reactive({
    isEdit: false
})

const fields = reactive({
    name: props.referenceMaterial.name,
    location: props.referenceMaterial.location,
});

const validation = reactive({
    fillError: false
})

const toggleIsEdit = () =>
{
    state.isEdit = !state.isEdit;
}

const submitReferenceEdit = async () =>
{
    if (fields.name.length < 1 || fields.location.length < 1)
    {
        validation.fillError = true;
        return;
    }
    else
    {
        validation.fillError = false;
    }

    const dbResponse = await fetch(`/api/referenceMaterial/edit/${props.referenceMaterial.referenceID}/${fields.name}/${fields.location}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const res = await dbResponse.text();

    console.log(res);
    toggleIsEdit();
}

const deleteReferenceMaterial = async () =>
{
    const dbResponse = await fetch(`/api/referenceMaterial/delete/${props.referenceMaterial.referenceID}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const res = await dbResponse.text();
    console.log(res, "res");
    // console.log(props.referenceMaterial.referenceID);
    
    if (res == "Success")
    {
        emits('updateTable'); 
    }
    // else
    // {
    //     console.log("Failed to delete reference material");
    // }
}

watchEffect(() => {
    fields.name = props.referenceMaterial.name;
    fields.location = props.referenceMaterial.location;
});

</script>

<style scoped>
.error
{
    color: red;
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
    background-color: #6eddd4;
}

#editBox 
{
    display: flex;
    flex-direction: row;
    justify-content: center;
}

#editButton
{
    width: 40%;
    padding: 1em;
    margin: .5em;
}
</style>

<!-- <tr v-for="(item, index) in referenceMaterial.material" :key="index">
        <td class="tableWrapper" v-if="!addReference.isEdit"> {{ item.name }} </td>
        <td class="tableWrapper" v-else> <input :value="item.name"/> </td>
        
        <td class="tableWrapper" v-if="!addReference.isEdit"> {{ item.location }}</td>
        <td class="tableWrapper" v-else> <input :value="item.location"/> </td>

        <td id="editBox" v-if="!addReference.isEdit"> 
            <button id="editButton" @click="toggleIsEdit"> Edit </button> 
            <button id="editButton"> Delete </button> 
        </td>
        <td id="editBox" v-else> 
            <button id="editButton" @click="toggleIsEdit"> Cancel </button> 
            <button id="editButton"> Submit </button> 
        </td>
    </tr> -->