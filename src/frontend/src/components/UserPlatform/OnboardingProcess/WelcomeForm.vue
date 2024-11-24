<template>
    <div class="welcomeContainer"> 
        <div class="welcomeWrapper">
            <h2> Welcome Serial Studier! </h2>
            <p id="welcomeText"> We will need some information to help you get started! </p>
            <button id="getStartedButton" @click="$emit('hideWelcomeComponent')"> Get Started! </button>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>

.welcomeContainer
{
    background-color: rgb(51, 51, 51);
    color: white;
    margin-top: auto;
    margin-bottom: auto;
    border-radius: 2em;
    overflow: hidden;
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

</style>