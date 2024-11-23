<template>
  <div class="appContainer">
    <NavBar />

    <section class="contentContainer">
        <RouterView />
  
    </section>
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import { RouterView } from 'vue-router';
</script>

<style>
html 
{
  font-family: 'Roboto', sans-serif;
  margin: 0;
  padding: 0;
  height: 100%;
}

body 
{
  margin: 0;
  padding: 0;
  height: 100%;
}

#app
{
  height: 100%;
}

.appContainer
{
  height: 100%;
}

.contentContainer
{
  background-color: aquamarine;
  height: 100%;
  display: flex;
  justify-content: center;
}
</style>
