<template>
    <nav :class="['nav', { 'hidden': hiddenPage }]">
        <div class="links">
            <RouterLink class="routerLink" to="/"> Home </RouterLink>
            <RouterLink class="routerLink" to="/login"> Login </RouterLink>
            <RouterLink class="routerLink" to="/accountManagerConsole"> AccountManagerConsole </RouterLink>
        </div>

        <div :class="['accountLinks', { 'hidden': hiddenPage }]">
            <span v-show="logStatus"> {{ account.name }} </span>
            <span v-show="!logStatus"> Log in </span>
        </div>
    </nav>
</template>

<script setup>
import { RouterLink, useRoute } from 'vue-router';
import { useAccountStore } from '@/stores/account';
import { computed } from 'vue';

const account = useAccountStore();
const route = useRoute();

const logStatus = computed (() => {
    return account.status;
})

const hiddenPage = computed(() =>
    route.name === "generateReport"
);


</script>

<style scoped>
.nav
{
    background-color: rgb(122, 120, 118);
    width: 100%;
    height: 60px;

    display: flex;
    flex-direction: row;
    justify-content: space-between;
    
}

.nav.hidden
{
    display: none;
}

.links
{
    margin-left: 1em;
    display: flex;
    justify-content: center;
    align-items: center;
}

.accountLinks
{
    margin-right: 1em;
    display: flex;
    justify-content: center;
    align-items: center;
}

.accountLinks.hidden 
{
    display: none;
}

.routerLink
{
padding-right: 1em;
}
</style>