<template>
    <div class="topicInfoComponent">
        <h2>Topic: {{ props.topic.name}}</h2>

        <div class="info">
            <div id="competencyDiv">
                <span> Competency: </span> 
                <SELECT :value="props.topic.competency" @change="updateCompetency($event.target.value)">
                    <option value="BEGINNER"> BEGINNER </option>
                    <option value="NOVICE"> NOVICE </option>
                    <option value="INTERMEDIATE"> INTERMEDIATE </option>
                    <option value="EXPERT"> EXPERT </option>
                    <option value="MASTER"> MASTER </option>
                </SELECT>
            </div>
            <div>
                <span> Learning Status:  </span>
                <SELECT :value="props.topic.learningStatus" @change="updateLearningStatus($event.target.value)">
                    <option value="INACTIVE"> INACTIVE </option>
                    <option value="REVIEW"> REVIEW </option>
                    <option value="MAINTAIN"> MAINTAIN </option>
                    <option value="ACTIVE"> ACTIVE </option>
                    <option value="GROWTH"> GROWTH </option>
                </SELECT>
            </div>
        </div>
    </div>
</template>

<script setup>
import { defineProps, reactive, onMounted } from "vue";


const props = defineProps({
    topic: Object
})

const topic = reactive({
    ...props.topic
});


onMounted(() => {
    topic.competency = props.topic.competency;
})

const updateCompetency = async (competency) =>
{
    const dbResponse = await fetch(`/api/topics/changeCompetency/${props.topic.topicID}/${competency}`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        }
    })
    
    const res = await dbResponse.text();

    console.log(res);
    //console.log(props.topic.topicID + competency);
}

const updateLearningStatus = async (learningStatus) =>
{
    const dbResponse = await fetch(`/api/topics/changeLearningStatus/${props.topic.topicID}/${learningStatus}`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        }
    })

    const res = await dbResponse.text();

    console.log(res);
}


</script>

<style setup>
.topicInfoComponent
{
    display: flex;
    justify-content: center;
    flex-direction: column;
    text-align: center;
    padding-bottom: 1em;
}

.info
{
    display: flex;
    flex-direction: row;
    justify-content: center;
}

#competencyDiv
{
    padding-right: 1em;
}
</style>