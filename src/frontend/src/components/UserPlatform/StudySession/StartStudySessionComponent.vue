<template>
    <div class="studyStartContainer">
        <h2>Start Study Session</h2>
        <div class="studyStartWrapper">
            <label> Time: </label>
            <select name="time" v-model="state.time">
                <option value="0.1">0.1 minutes</option>
                <option value="1">1 minutes</option>
                <option value="5">5 minutes</option>
                <option value="15">15 minutes</option>
                <option value="30">30 minutes</option>
                <option value="45">45 minutes</option>
                <option value="60">60 minutes</option>
            </select>
        </div>
        <button id="startButton" @click="start"> Start </button>
    </div>
</template>

<script setup>
import { defineEmits, reactive } from "vue"


const emit = defineEmits(['updateTime'])

const state = reactive({
    time: 30
})

const start = () =>
{
    emit('updateTime', state.time);
}
</script>

<style scoped>
h2
{
    margin: 0;
    padding: 0.5em 0 1em 0;
}

.studyStartContainer
{
    background-color: rgb(77, 241, 253);
    width: 300px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    border-radius: 1em;
    padding: 1em;
    margin-bottom: 4em;
}

.studyStartWrapper
{
    padding-bottom: 2em;
}

#startButton
{
    margin-bottom: 1em;
    width: 100px;
    height: 30px;
}
</style>