<template>
  <div class="appContainer">

    <NavBar />

    <div class="contentContainer">
      <RouterView />
    </div>
  
  </div>
</template>

<script setup>
import NavBar from "@/components/DebugNav.vue";
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

#spacer 
{
  height: 3em;
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
  display: flex;
  flex-direction: column;
}

.contentContainer
{
  height: 100%;
  flex: 1;
  overflow: hidden;
}

</style>
