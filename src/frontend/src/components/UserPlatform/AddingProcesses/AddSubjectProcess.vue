<template>
    <div class="welcomeContainer"> 
        <div class="welcomeWrapper">
            <div class="backLinkContainer">
                <span class="learningMapLink" @click="goToLearningMap"> Back to LearningMap... </span>
            </div>
            <p id="welcomeText"> Enter Subject </p>
            <div class="inputSection">
                <div class="dropDown">
                    <input class="input firstInput" placeholder="Subject" type="text" v-model="userInput.subject"/>        
                </div>
                <button @click="submitSubject">
                    Submit
                </button>
                <p v-if="validation.fillError" class="error"> Need to fill the field </p>
                <p v-if="validation.lengthError" class="error"> Max 25 characters </p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { reactive } from "vue";
import { useAccountStore } from "@/stores/account";
import { useRouter } from "vue-router";


const account = useAccountStore();
const router = useRouter();

const userInput = reactive({
    subject: ''
});

const validation = reactive({
    fillError: false,
    lengthError: false
});

const submitSubject = async () =>
{
    if (userInput.subject.length < 1)
    {
        validation.fillError = true;
        return;
    }
    else
    {
        validation.fillError = false;
    }

    if (userInput.subject.length > 25)
    {
        validation.lengthError = true;
        return;
    }
    else
    {
        validation.lengthError = false;
    }

    const dbResponse = await fetch(`/api/subjects/create`, {
        method: 'POST',
        headers:
        {
            "Content-Type" : "application/json",
        },
        body: JSON.stringify({
            name: userInput.subject,
            uuid: account.uuid,
        })
    });

    if (dbResponse.ok)
    {
        console.log("success");
        router.push("/platform/learningMap");
    }
    else
    {
        console.log("Failure");
    }
}

const goToLearningMap = () =>
{
    router.push("/platform/learningMap");
}

</script>

<style scoped>

.error
{
    color: red;
}

.backLinkContainer
{
    text-align: left;
    color: rgb(93, 164, 223);
    cursor: pointer;
}

.learningMapLink:hover
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


button {
    background-color: rgb(230, 230, 230);
    padding: 1em 2em;
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