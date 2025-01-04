<template>
  <section id="homeMainSection">
    <h1 id="homeTitle"> Welcome to Serial Studier v.01 </h1>
    <p id="tagLineHome"> Your one-stop shop for automizing your study habits!</p>
    <div class="logoContainer">
        <img id="logo" :src="logo" width="200" height="200"/>
    </div>

  </section>

  <section v-if="account.status" id="loginContainerHome">
    <h3 id="h3Home"> You are logged in! Go to the platform </h3>
    <div id="homeButtonContainer">
      <button v-show="loggedIn" @click="goDashboard"> Go to Dashboard </button>  
    </div>
  </section>

  <section v-if="!account.status" id="loginContainerHome">
    <h3 id="h3Home"> You need to login! </h3>
    <div id="homeButtonContainer">
      <button @click="goToLogin"> Go to Login Page </button>  
    </div>
  </section>

  
  
</template>

<script setup>
import logo from '@/assets/mindMapPngTree.png';
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

const goToLogin = () =>
{
  router.push('/login');
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

#homeMainSection
{
  background-color: #E56B6F;
  text-align: center;
}

#homeTitle
{
  padding-top: 1em;
  margin-top: 0;
  font-size: 3em;
}

#tagLineHome
{
  font-size: 2em;
}

#loginContainerHome
{
  background-color: #355070;
}

#h3Home
{
  color: white;
  margin-top: 0;
  padding-top: 1em;
  text-align: center;
  font-size: 3em;
}

#homeButtonContainer
{
  display: flex;
  justify-content: center;
  padding-bottom: 3em;
}

#homeButtonContainer button
{
  font-size: 22px;
  height: 100px;
  width: 300px;
}
</style>
  