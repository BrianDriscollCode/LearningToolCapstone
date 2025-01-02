<template>
    <h1> Congratulations! </h1>
    <button @click="goToDashboard"> Back to dashboard </button>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const goToDashboard = () => 
{
    router.push("/platform/dashboard");
}
</script>