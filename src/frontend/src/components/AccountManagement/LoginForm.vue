<template>
  <section class="formContainer">
    <img id="xPNG" :src="xPNG" width="15" height="15" @click="exit"/>
    <div class="formWrapper">
      <div class="logoContainer">
        <img id="logo" :src="logo" width="100" height="100"/>
      </div>
      
      <h2 id="loginTitle" v-if="!account.status"> Sign in to SerialStudier </h2>
      <h2 id="loginTitle" v-if="account.status"> Logout of SerialStudier </h2>

        

      <form @submit.prevent="login" v-if="!account.status">
        
        <div class="formInnerWrapper">
          <div class="orSpacing">
            <hr />
          </div>

          <input id="emailInput" type="email" placeholder="Email"  v-model="userInfo.email" />
          <br />
          <input id="passwordInput" type="password" placeholder="Password" v-model="userInfo.password" /> 
          <button id="loginButton" @click="login"> Login </button> 

          <p v-if="validation.emailError" class="error"> Wrong email or password... </p> 
          <!-- Leaving sign up off because would require different license for supabase to have unknown accounts to signup -->
          <p> Don't have an account? <RouterLink to="/signup" id="signUpLink"> Sign up </RouterLink> </p>
          
        </div>
        
      </form>

      <form @submit.prevent="logout" v-if="account.status">
        
        <div class="formInnerWrapper">
        
          <button id="loginButton" @click="logout"> Logout </button> 

          
        </div>
        
      </form>
    </div>
  </section> 
</template>

<script setup>
import { supabase } from '@/clients/supabase';
import { reactive } from 'vue';
import { useRouter } from "vue-router";
import { useAccountStore } from '@/stores/account';
//import { RouterLink } from 'vue-router';
import { config } from "@/config"
import logo from '@/assets/brain_logo.png';
import xPNG from "@/assets/exitFlaticon.png";

let userInfo = reactive({
  email: "bdriscoll407@gmail.com",
  password: "Imcool123!",
});

const validation = reactive({
  emailError: false,
  fillError: false
})

const router = useRouter();
const account = useAccountStore();

async function login()
{
  const { data, error } = await supabase.auth.signInWithPassword({
    email: userInfo.email,
    password: userInfo.password
  });
  if (error)
  {
    if (config.debug) console.log(error);
    validation.emailError = true;
  }
  else
  {
    if (data.user)
    {
      validation.emailError = false;
      // updates pinia store
      account.status = true;
      let uuid = data.user.id;
      try 
      {
        const response = await fetch(`/api/users/by-uuid/${uuid}`);
        const userData = await response.json();
    
        if (config.debug) console.log("COMPONENT - LOGIN FORM: " + userData);

        account.name = userData.name;
        account.uuid = uuid;

        if (userData.employee)
        {
            account.isAdmin = userData.employee.is_Admin;
        }
        else
        {
            account.isAdmin = false;
        }
        

        router.push('/platform/dashboard');
      }
      catch (error)
      {
        if (config.debug) console.log("COMPONENT - LOGIN FORM: " + error);
      }
      
    }
  }
}

const logout = async () =>
{

  const { error } = await supabase.auth.signOut();
  account.logOut();

  if (error)
  {
    console.log(error);
  }
  else 
  {
    console.log("Sign out success");
    account.status = false;
    account.name = '';
    account.isAdmin = false;
  }
}

async function exit()
{
  router.push('/')
}

</script>

<style scoped>
.error
{

  color: red;
}

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

#emailInput
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

#passwordInput
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

#signUpLink
{
  color: #828dd4;
}

#signUpLink:hover
{
  color: #0a21c0;
}

#signUpLink:active
{
  color: #828dd4;
}
</style>

