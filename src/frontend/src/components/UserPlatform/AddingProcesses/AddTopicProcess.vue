<template>
    <div class="welcomeContainer"> 
        <div class="welcomeWrapper">
            <span class="backLink" @click="goToLearningMap"> Back to LearningMap... </span>
            <p id="welcomeText"> Enter Topic for {{ name.name }}</p>
            <div class="inputSection">
                <div class="dropDown">
                    <input class="input firstInput" placeholder="Topic" type="text" v-model="newTopicInput.name"/>
                    <select class="input secondInput" v-model="newTopicInput.competency">
                        <option value="Beginner">Beginner</option>
                        <option value="Novice">Novice</option>
                        <option value="Intermediate">Intermediate</option>
                        <option value="Expert">Expert</option>
                        <option value="Master">Master</option>
                    </select>

                    <select class="input" v-model="newTopicInput.learning_status">
                        <option value="Review">Review</option>
                        <option value="Maintain">Maintain</option>
                        <option value="Active">Active</option>
                        <option value="Growth">Growth</option>
                    </select>
                    
                </div>
                <button @click="addTopic(name.id, newTopicInput.name, newTopicInput.competency, newTopicInput.learning_status)">
                    Submit
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { defineProps, reactive } from 'vue';
import { useAccountStore } from '@/stores/account';

const account = useAccountStore();
const router = useRouter();

console.log(account.uuid);

const name = defineProps({
    name: String,
    id: Number
});

console.log(name.id);

const newTopicInput = reactive({
    uuid: account.uuid,
    competency: "",
    learning_status: "",
    name: ""
})

const addTopic = async (id, name, competency, learning_status) => {
    const comp = competency.toUpperCase();
    const learn = learning_status.toUpperCase();

    const dbResponse = await fetch(`/api/topics/createTopic/${id}/${name}/${comp}/${learn}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    });

    // Handle response
    const res = await dbResponse.text();
    
    if (res)
    {
        router.push("/platform/learningMap/");
    }
};

const goToLearningMap = () => 
{
    router.push("/platform/learningMap");
}

</script>

<style scoped>
.backLink
{
    text-align: left;
    color: rgb(93, 164, 223);
    cursor: pointer;
}

.backLink:hover
{
    cursor: pointer;
    color: rgb(245, 159, 1);
}


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
    margin-bottom: 1em;
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
    padding-bottom: 0em;
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

.secondInput
{
    margin-right: 1em;
}

.dropDown
{
    display: flex;
    justify-content: center;
    flex-direction: row;
}

button {
    background-color: rgb(230, 230, 230);
    padding: 1em 2em;
    margin: 5px;
    border: none;
    cursor: pointer;
    border-radius: 4px;
    font-size: 14px;
    transition: background-color 0.3s ease;
    margin-bottom: 1em;
}

button:hover
{
    background-color: rgb(123, 255, 255);
}
</style>