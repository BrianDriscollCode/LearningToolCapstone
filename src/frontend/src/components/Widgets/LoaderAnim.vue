<template>
    <div class="loader"> </div> 
</template>

<script setup>

</script>

<style scoped>
.loader {
  width: 150px;
  height: 150px;
  border: 16px solid #034646;
  border-top: 16px solid #539616;
  border-radius: 50%;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>