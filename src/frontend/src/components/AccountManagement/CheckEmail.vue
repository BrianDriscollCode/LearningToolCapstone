<template>
    <section class="formContainer">
      <img id="xPNG" :src="xPNG" width="15" height="15" @click="exit"/>
      <div class="formWrapper">
        <div class="logoContainer">
          <img id="logo" :src="logo" width="100" height="100"/>
        </div>
  
        <h2 class="checkEmail"> Check Email </h2>
        
      </div>
    </section> 
  </template>
  
<script setup>
import logo from "@/assets/mindMapPngTree.png"
</script>
  
<style scoped>
  
  
  .formContainer 
  {
    display: flex;
    flex-direction: column;
    padding: 1em;
    background-color: rgb(36, 36, 36);
    width: 700px;
    margin-top: auto;
    margin-bottom: auto;
    border-radius: .5em;
    color:white;
  }

  .checkEmail
  {
    font-size: 2em;
    text-align: center
  }
  
  
  .formWrapper
  {
    width: 55%;
    margin-left: auto;
    margin-right: auto;
  }
  
  .logoContainer
  {
    display: flex;
    justify-content: center;
    margin: 0 0 2em 0;
  }
  
  #loginTitle 
  {
    padding: 0;
    margin: 0 0 1em 0;
  }
  
  .formInnerWrapper
  {
    justify-content: center;
    flex-direction: column;
  }
  .orSpacing 
  {
   
    margin: 0 0 1em 0;
  }
  
  #googleSignInButton
  {
    width: 100%;
    height: 40px;
    margin: 0 0 1em 0;
    border-radius: 2em;
    border: .5px solid rgb(211, 211, 211);
  }
  
  .accountInput
  {
    box-sizing: border-box;
    width: 100%;
    height: 50px;
    margin-bottom: 2em;
    padding: 0 0 0 1em;
    margin: 0 0 2em 0;
    border: 1px solid #ccc;
    font-size: 1em;
  }
  
  #loginButton
  {
    width: 100%;
    height: 40px;
    margin: 0 0 2em 0;
    border-radius: 2em;
    border: .5px solid rgb(211, 211, 211);
  
  }
</style>
  
  