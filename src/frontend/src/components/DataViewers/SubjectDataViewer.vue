<template>
    <div class="dataContainer">
        <div v-if="loading"> 
            ...Loading
        </div>
        <div v-if="!loading">
            <div class="userID">
                <p> Subjects Table </p>
                <div class="subjects" v-for="(subject, index) in data" :key="index">
                  <span> Subject ID </span>  
                  {{ subject.subjectID }}
                  <br />

                  <span> Name: </span>
                  {{ subject.name }}
                  <br />

                  <span> User: </span>
                  {{ subject.userID }}
                  <br />
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
    const response = await fetch("/api/subjects");
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

.subjects
{
  padding-top: .5em;
}
</style>