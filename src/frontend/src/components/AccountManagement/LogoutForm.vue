<template>
  <div class="formContainer">
    <h2> Logout </h2>

    <form @submit.prevent="logout"> 
      <div>
        <button @click="logout"> Logout </button> 
      </div>
    </form>

    <hr />

    <button> Go to Dashboard </button>
  </div> 
</template>

<script setup>
import { supabase } from '@/clients/supabase';
import { reactive } from 'vue';
import { config } from "@/config"
import { useAccountStore } from '@/stores/account';

let userInfo = reactive({
  email: "",
  password: "",
});

const account = useAccountStore();

async function logout()
{
  account.logOut();
  account.isAdmin = false;
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