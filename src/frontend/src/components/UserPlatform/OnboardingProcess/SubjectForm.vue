<template>
    <div class="welcomeContainer"> 
        <div class="welcomeWrapper">
            <p id="welcomeText"> Enter in a subject you are learning (i.e. Algorithms, Calculus, History, etc) </p>
            <div class="inputSection">
                <input class="input" placeholder="First Subject" type="text" v-model="subject" />
                <button 
                    id="getStartedButton"
                     @click="submitSubject"
                    > Next 
                </button>
                <p v-if="validation.fillError" id="error"> You must fill in the field </p>
                <p v-if="validation.lengthError" id="error"> Max 25 characters </p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive, defineEmits } from "vue";

let subject = ref("");

const validation = reactive({
    fillError: false,
    lengthError: false
})

const emit = defineEmits(['fillSubjects']);

const submitSubject = () =>
{
    if (subject.value.length > 25)
    {
        validation.lengthError = true;
    }
    else if (subject.value.length < 1)
    {
        validation.fillError = true;
    }
    else
    {
        validation.error = false;
        emit("fillSubjects", subject);
    }
}

</script>

<style scoped>

#error 
{
    color: red;
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
    width: 70%;
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
</style>