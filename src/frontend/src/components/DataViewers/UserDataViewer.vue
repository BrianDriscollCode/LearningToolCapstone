<template>
    <div class="dataContainer">
        <div v-if="loading"> 
            ...Loading
        </div>
        <div v-if="!loading">
            <div class="userID">
                <p> Users Table </p>
                <div class="user" v-for="(user, index) in data" :key="index">
                    <span> Name: </span>
                    {{ user.name }}
                    <br />
                    <span> Last Login: </span>
                    {{ user.lastLogin}}
                </div>
            </div>
            
            <div class="subjectID">
                
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

let data = ref([]);
let loading = ref(true);


const fetchUsers = async () => 
{
  try 
  {
    const response = await fetch("/api/users");
    data.value = await response.json();
    loading.value = false;


  }
  catch (err)
  {
    err.value = err.message;
  }
}

onMounted(() => {
  fetchUsers();
  console.log("mounted");
});
</script>

<style scoped>
body 
{
  margin: 0;
  padding: 0;
}

.dataContainer 
{
    background-color: rgb(193, 251, 255);
}

.user 
{
  padding-top: .5em;
}
</style>