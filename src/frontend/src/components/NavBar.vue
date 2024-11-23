<template>
    <nav>
        <RouterLink class="routerLink" to="/"> Home </RouterLink>
        <RouterLink class="routerLink" to="/login"> Login </RouterLink>
    </nav>
</template>

<script setup>
import { RouterLink } from 'vue-router';
</script>

<style scoped>
nav
{
    background-color: rgb(122, 120, 118);
    position: absolute;
    width: 100%;
    height: 3em;
}

.routerLink
{
padding-right: 1em;
}
</style>