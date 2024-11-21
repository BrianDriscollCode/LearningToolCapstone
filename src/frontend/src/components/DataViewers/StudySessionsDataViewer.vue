<template>
    <div class="dataContainer">
        <div v-if="loading"> 
            ...Loading
        </div>
        <div v-if="!loading">
            <div class="userID">
                <p> Study Session Table </p>
                <div class="subjects" v-for="(studySessions, index) in data" :key="index">
                  <span> Study Session ID: </span>  
                  {{ studySessions.studySessionsID }}
                  <br />

                  <span> User ID: </span>  
                  {{ studySessions.userID }}
                  <br />

                  <span> Topic ID: </span>  
                  {{ studySessions.topicID }}
                  <br />

                  <span> Completed: </span>  
                  {{ studySessions.completed}}
                  <br />

                  <span> MoreStudyRequired: </span>  
                  {{ studySessions.moreStudyRequired}}
                  <br />

                  <span> Date: </span>  
                  {{ studySessions.date}}
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
    const response = await fetch("/api/studySessions");
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