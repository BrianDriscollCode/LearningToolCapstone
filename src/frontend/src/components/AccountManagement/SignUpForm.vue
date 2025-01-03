<template>
  <section class="formContainer">
    <img id="xPNG" :src="xPNG" width="15" height="15" @click="exit"/>
    <div class="formWrapper">
      <div class="logoContainer">
        <img id="logo" :src="logo" width="100" height="100"/>
      </div>
      
      <h2 id="loginTitle"> Sign up to SerialStudier </h2>

        
      <form @submit.prevent="login">
        
        <div class="formInnerWrapper">

          <div class="orSpacing">
            <hr />
          </div>

          <!--- turn to class instead of id-->
          <input class="accountInput" type="email" placeholder="Email" v-model="userInfo.email" />
          <br />
          <input class="accountInput" type="password" placeholder="Password" v-model="userInfo.password" /> 
          <br />
          <input class="accountInput" type="text" placeholder="First Name" v-model="userInfo.firstName" /> 
          
          <button id="loginButton" @click="createAccount"> Sign up </button> 
          
        </div>
        
      </form>
    </div>
  </section> 
</template>

<script setup>
import { supabase } from '@/clients/supabase';
import { reactive, defineEmits } from 'vue';
import { useRouter } from "vue-router";
import { config } from "@/config"
import logo from '@/assets/mindMapPngTree.png';
import xPNG from "@/assets/exitFlaticon.png";

let userInfo = reactive({
  email: "bdriscoll407@gmail.com",
  password: "Imcool123!",
  firstName: "Brian"
});

const router = useRouter();
const emit = defineEmits(["signUpComplete"]);

async function createAccount()
{
  const { data, error } = await supabase.auth.signUp(
    {
      email: userInfo.email,
      password: userInfo.password,
      firstName: userInfo.firstName
    }
  );
  
  if (error)
  {
    if (config.debug) console.log(error);
  }
  else
  {
    if (config.debug) console.log(data.user.id);
    try 
    {
      const response = await fetch('/api/users/create', {
        method: "POST",
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ 
          uuid: data.user.id,
          email: userInfo.email,
          name: userInfo.firstName
          
        })
      });

      if (!response.ok)
      {
        if (config.debug) console.error("SIGNUPFORM: Failed to create user - " + response);
      }
      else
      {
        if (config.debug) console.log("SIGNUP FORM: User created successfully!")
        emit("signUpComplete");
      }
    } 
    catch (error)
    {
      if (config.debug) console.error("SIGNUP FORM: " + error);
    }
  }
}

async function exit()
{
  router.push("/");
}

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

