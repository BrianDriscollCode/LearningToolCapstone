<template>
    <div class="addingTopicContainer">
        <AddTopicProcess :name="name" :id="subjectID" />
    </div>
</template>

<script setup>
import AddTopicProcess from '@/components/UserPlatform/AddingProcesses/AddTopicProcess.vue';
import { defineProps } from "vue";

const props = defineProps({
    name: {
        type: String,
        required: true
    },
    subjectID: {
        type: Number,
        required: true
    }
});

console.log(props.subjectID, " -subjectID");
console.log(props.name + " - name");


</script>

<style scoped>
.addingTopicContainer
{
    height: 100%;
    background-color: rgb(82, 74, 65);
    display: flex;
    align-items: center;
    flex-direction: column;
   
}
</style>