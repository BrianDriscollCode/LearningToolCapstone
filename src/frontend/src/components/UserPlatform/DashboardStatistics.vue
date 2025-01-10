<template>

    <div class="statisticsContainer">
        <h3> Your Stats: </h3>
        <p> Study Sessions completed: {{ sessions.numberCompleted }}</p>
    </div>

</template>

<script setup>
import { reactive, defineProps, watch } from "vue";

const props = defineProps({
    uuid: {
        type: String,
        required: true
    }
})

const sessions = reactive({
    numberCompleted: 0 
})

const getSessionsCompleted = async (uuid) =>
{
    try 
    {
        const dbResponse = await fetch(`/api/studySessions/studySessionsCompleted/${uuid}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });
        const res = await dbResponse.text();
        console.log("Dash Statistics: " + uuid);

        console.log("Dash Statistics: " + res);

        sessions.numberCompleted = res;
    }
    catch (error)
    {
        console.log(error);
    }
    
}

watch(() => props.uuid, (uuid) => {
    getSessionsCompleted(uuid);
})

</script>

<style scoped>
.statisticsContainer {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px #4e4e4e;
    border: 1px solid #e0e0e0;
    width: 300px;
    margin: 20px auto;
}

.statisticsContainer h3 {
    font-size: 1.25em;
    font-weight: 600;
    color: #333;
    margin-bottom: 1em;
    margin-top: 1em;
}

.statisticsContainer p {
    font-size: 1rem;
    color: #666;
    margin: 5px 0;
}

.statisticsContainer p:first-child {
    font-weight: 500;
}
</style>