<template>
  <div class="formContainer">
    <h2> Logout </h2>

    <form @submit.prevent="logout"> 
      <div>
        <button @click="login"> Logout </button> 
      </div>
    </form>
  </div> 
</template>

<script setup>
import { supabase } from '@/clients/supabase';
import { reactive } from 'vue';
import { config } from "@/config"

let userInfo = reactive({
  email: "",
  password: "",
});

async function logout()
{
  const { error } = await supabase.auth.signOut();

  if (error)
  {
    if (config.debug) console.log(error);
  }
  else 
  {
    if (config.debug) console.log("Sign out success");
  }
}

</script>

<style scoped>

.formContainer 
{
  height: 300px;
}

</style>