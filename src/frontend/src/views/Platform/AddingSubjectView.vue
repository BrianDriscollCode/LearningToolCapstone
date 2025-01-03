<template>
    <div class="addingSubjectContainer">
        <AddSubjectProcess />
    </div>
    
</template>

<script setup>
import AddSubjectProcess from '@/components/UserPlatform/AddingProcesses/AddSubjectProcess.vue';

</script>

<style scoped>
.addingSubjectContainer
{
    height: 100%;
    background-color: #6D597A;
    display: flex;
    align-items: center;
    flex-direction: column;
   
}

.otherOptionsContainer
{
    margin-top: 1em;
    display: flex;
    justify-content: center;
    flex-direction: column;
}
</style>