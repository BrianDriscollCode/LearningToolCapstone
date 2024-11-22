<template>
  <div>
    <h2> Login Form </h2>

    <form @submit.prevent="login">
      <input type="email" placeholder="Email" v-model="userInfo.email" />
      <input type="password" placeholder="Password" v-model="userInfo.password" /> 
      <div>
        <button @click="login"> Login </button> 
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