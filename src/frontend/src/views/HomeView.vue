<template>
  <h1> Home </h1>

  <button v-show="loggedIn" @click="goDashboard"> Go to Dashboard </button>  
  
</template>

<script setup>
import { useAccountStore } from '@/stores/account';
import { computed } from "vue";
import { useRouter } from "vue-router";

const account = useAccountStore();
const router = useRouter();

const loggedIn = computed(() => account.status);

const goDashboard = () => 
{
  router.push('/platform/dashboard');
}

</script>

<style>
html 
{
  margin: 0;
  padding: 0;
}

body 
{
  margin: 0;
  padding: 0;
}

</style>
  