<template>
  <div>
    <NavBar />

    <div class="content">
      <RouterView />
    </div>
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import { RouterView } from 'vue-router';
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
