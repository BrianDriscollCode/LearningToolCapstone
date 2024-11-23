<template>
  <section class="formContainer">
    <img id="xPNG" :src="xPNG" width="15" height="15"/>
    <div class="formWrapper">
      <div class="logoContainer">
        <img id="logo" :src="logo" width="100" height="100"/>
      </div>
      
      <h2 id="loginTitle"> Sign in to SerialStudier </h2>

        

      <form @submit.prevent="login">
        
        <div class="formInnerWrapper">

          <button id="googleSignInButton"> Sign in with Google </button>
          <div class="orSpacing">
            <hr />
          </div>

          <input id="emailInput" type="email" placeholder="Email" v-model="userInfo.email" />
          <br />
          <input id="passwordInput" type="password" placeholder="Password" v-model="userInfo.password" /> 
          <button id="loginButton" @click="login"> Login </button> 

          <p> Don't have an account? <a href="#"> Sign up</a> </p>
          
        </div>
        
      </form>
    </div>
  </section> 
</template>

<script setup>
import { supabase } from '@/clients/supabase';
import { reactive } from 'vue';
import logo from '@/assets/mindMapPngTree.png';
import xPNG from "@/assets/exitFlaticon.png";

let userInfo = reactive({
  email: "",
  password: "",
});

async function login()
{
  const { data, error } = await supabase.auth.signInWithPassword({
    email: userInfo.email,
    password: userInfo.password
  });
  if (error)
  {
    console.log(error);
  }
  else
  {
    console.log(data);
  }
}

</script>

<style scoped>


.formContainer 
{
  display: flex;
  flex-direction: column;
  padding: 1em;
  background-color: beige;
  height: 70%;
  width: 700px;
  margin-top: auto;
  margin-bottom: auto;
  border-radius: .5em;
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

#emailInput
{
  box-sizing: border-box;
  width: 100%;
  height: 50px;
  margin-bottom: 2em;
  padding: 0;
  margin: 0 0 2em 0;
  border: 1px solid #ccc;
}

#passwordInput
{
  box-sizing: border-box;
  width: 100%;
  height: 50px;
  margin-bottom: 2em;
  padding: 0;
  margin: 0 0 2em 0;
  border: 1px solid #ccc;
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

