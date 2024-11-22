<template>
  <div>
    <h2> Login Form </h2>

    <form @submit.prevent="createAccount">
      <input type="email" placeholder="Email" v-model="userInfo.email" />
      <input type="password" placeholder="Password" v-model="userInfo.password" /> 
      <div>
        <button @click="createAccount"> Create </button> 
        <button @click="login"> Login </button> 
        <button @click="seeCurrentUser"> See user </button>
        <button @click="getSession"> GetSession </button>
        <button @click="logout"> Logout </button>

      </div>
    </form>
  </div> 
</template>

<script setup>
import { supabase } from '@/clients/supabase';
import { reactive } from 'vue';

let userInfo = reactive({
  email: "",
  password: "",
});

//let user = ref();

async function createAccount()
{

  const { data, error } = await supabase.auth.signUp({
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

async function getSession()
{
  const { data, error } = await supabase.auth.getSession();

  if (error)
  {
    console.log(error);
  }
  else 
  {
    console.log(data);
  }
}

async function seeCurrentUser()
{
  const localUser = await supabase.auth.getSession();
  console.log(localUser.data.session);
}

async function logout()
{
  const { error } = await supabase.auth.signOut();

  if (error)
  {
    console.log(error);
  }
  else 
  {
    console.log("Sign out success");
  }
}



</script>