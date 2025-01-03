<template>
    <h1> Congratulations! </h1>
    <div id="buttonContainer">
        <button @click="goToDashboard"> Back to dashboard </button>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const goToDashboard = () => 
{
    router.push("/platform/dashboard");
}
</script>

<style scoped>
h1
{
    text-align: center;
    color: white;
}

#buttonContainer
{
    width: 100%;
    display: flex;
    justify-content: center;
}

button
{
    width: 300px;
    height: 80px;
    font-size: 18px;
}
</style>