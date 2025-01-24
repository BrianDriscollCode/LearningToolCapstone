<template>
    <section class="pageContainer">
        <LoginForm />
    </section>
</template>

<script setup>
import LoginForm from '@/components/AccountManagement/LoginForm.vue';
</script>

<style scoped>
.pageContainer
{
    flex: 1;
    height: 100%;
    width: 100%;
    background-color: #b3b4bd;
    display: flex;
    justify-content: center;
    overflow: hidden;
}
</style>