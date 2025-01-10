<template>
    <section class="studySectionContainer">
        <div v-if="!state.started">
            <StartStudySessionComponent 
                :updateTime="setTime"
                @updateTime="setTime"
            />
        </div>
        <div id="sessionStartedWrapper" v-else>
            <h1> {{ props.topicName }}</h1>
            <button @click="completeSession" id="completeButton"> Complete </button>
            <TimeTrackingBar 
                :time="state.time" 
                :completeSession="completeSession"
                @completeSession="completeSession"
            />
        </div>
    </section>
    
    
</template>

<script setup>
import TimeTrackingBar from '@/components/UserPlatform/StudySession/TimeTrackingBar.vue';
import StartStudySessionComponent from '@/components/UserPlatform/StudySession/StartStudySessionComponent.vue';
import { reactive, defineProps } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const state = reactive ({
    started: false,
    time: 0
});

const setTime = (time) =>
{
    state.time = time;
    state.started = true;
}

const completeSession = async () =>
{
    const dbResponse = await fetch(`/api/studySessions/complete/${props.studySessionID}`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        }
    });
    const res = await dbResponse.text();
    console.log(res);

    if (res)
    {
        router.push(`/platform/completed/${props.studySessionID}/${props.topicName}`);
    }
    else
    {
        console.log("Study session backend error");
    }
    
}

const props = defineProps({
    studySessionID: {
        type: Number,
        required: true
    },
    topicName: {
        type: String,
        require: true
    }
})

</script>

<style scoped>
.studySectionContainer
{
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

#sessionStartedWrapper
{
    width: 80%;
    text-align: center;
}

#completeButton
{
    padding: 1em;
    margin-bottom: 1em;
}

</style>