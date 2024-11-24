<template>
    <div class="welcomeContainer"> 
        <div class="welcomeWrapper">
            <p id="welcomeText"> Enter in two topics within the {{ props.subject }} subject (i.e. civil war, linked lists, derivatives, etc) and your competency level: </p>
            <div class="inputSection">
                <div class="dropDown">
                    <input class="input firstInput" placeholder="First Topic" type="text" v-model="topics.one" />
                    <select class="input" v-model="topics.oneCompetency">
                        <option value="Beginner">Beginner</option>
                        <option value="Novice">Novice</option>
                        <option value="Intermediate">Intermediate</option>
                        <option value="Expert">Expert</option>
                        <option value="Master">Master</option>
                    </select>
                </div>
                <div class="dropDown">
                    <input class="input firstInput" placeholder="Second Topic" type="text" v-model="topics.two" />
                    <select class="input" v-model="topics.twoCompetency">
                        <option value="Beginner">Beginner</option>
                        <option value="Novice">Novice</option>
                        <option value="Intermediate">Intermediate</option>
                        <option value="Expert">Expert</option>
                        <option value="Master">Master</option>
                    </select>
                </div>
                <button 
                    id="getStartedButton"
                     @click="$emit('fillTopics', topics)"
                    > Next 
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { reactive, defineProps } from "vue";

let topics = reactive({
    one: "",
    oneCompetency: "",
    two: "",
    twoCompetency: ""
})

const props = defineProps({
    subject: String
});

</script>

<style scoped>

.welcomeContainer
{
    background-color: rgb(51, 51, 51);
    color: white;
    margin-top: auto;
    margin-bottom: auto;
    border-radius: 2em;
    width: 500px;
}

.welcomeWrapper
{
    display: flex;
    justify-content: center;
    flex-direction: column;
    padding: 1em;
    text-align: center;
}

h2 
{
    margin-top: 0.5em;
    margin-bottom: 0;
    padding-bottom: 0em;
}

#welcomeText
{
    font-size: 1.4em;
    padding-bottom: 1em;
}

#getStartedButton
{
    width: 100%;
    height: 40px;
    margin: 0 0 1em 0;
    border-radius: 2em;
    border: .5px solid rgb(211, 211, 211);
}

.inputSection
{
    display: flex;
    justify-content: center;
    flex-direction: column;
    width: 80%;
    margin-left: auto;
    margin-right: auto;
}

.input
{
    box-sizing: border-box;
    width: 100%;
    height: 50px;
    margin-bottom: 2em;
    padding: 0 0 0 1em;
    margin: 0 0 2em 0;
    border: 1px solid #ccc;
    font-size: 1em;
    margin-bottom: 2em;
}

.firstInput
{
    margin-right: 1em;
}

.dropDown
{
    display: flex;
    justify-content: center;
    flex-direction: row;
}
</style>