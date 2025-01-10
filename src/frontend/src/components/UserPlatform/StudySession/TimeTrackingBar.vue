<template>
    <div id="bar"> 
        <div id="fillColor" :style="{ width: state.percentage + '%' }"> </div>
    </div>
</template>

<script setup>
import { reactive, onMounted, defineProps, defineEmits } from "vue";

const props = defineProps({
    time: {
        type: Number,
        required: true 
    },

});

const state = reactive({
    percentage: 0,
    time: props.time,
});

const emit = defineEmits(['completeSession']);

let timer;

const startCountdown = () => {
    const totalDuration = state.time * 60;
    let elapsed = 0;

        timer = setInterval(() => 
        {
            elapsed += 1;
            state.percentage = (elapsed / totalDuration) * 100;

            if (state.percentage >= 100)
            {
                clearInterval(timer);
                emit("completeSession")
            }

        }, 1000); 
    };

onMounted(() => {
    startCountdown();
})

</script>

<style scoped>
#bar 
{
    position: relative;
    overflow: hidden;
    background-color: rgb(148, 170, 170);
    width: 80%;
    height: 50px;
    margin-left: auto;
    margin-right: auto;
    border: 3px solid rgb(209, 211, 86);
}

#fillColor 
{
    position: absolute;
    background-color: rgb(83, 241, 175); 
    height: 100%;
    top: 0;
    left: 0;
}
</style>