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
.routerLink
{
padding-right: 1em;
}
</style>