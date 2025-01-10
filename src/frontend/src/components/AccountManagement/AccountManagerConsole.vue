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

      <div>
        <h3> Set Name </h3>
        <input type="text" placeholder="changeName" v-model="userName"/>
        <button @click="insertName"> Test </button>
      </div>

      <div>
        <h3> Insert Account </h3>
        <input type="text" placeholder="Email" v-model="insertAccountInfo.email"/>
        <input type="password" placeholder="Password" v-model="insertAccountInfo.password"/>
        <input type="name" placeholder="name" v-model="insertAccountInfo.name"/>
        <button @click="insertAccount"> Test </button>
      </div>

      <div>
        <h3> Create Subject </h3>
        <input type="text" placeholder="Name" v-model="insertSubjectInfo.name"/>
        <input type="text" placeholder="userID" v-model="insertSubjectInfo.uuid"/>
        <button @click="createSubject"> Submit Subject </button>
      </div>

      <div>
        <h3> Create Topic </h3>
        <input type="text" placeholder="Name" v-model="insertTopicInfo.name"/>
        <input type="text" placeholder="userID" v-model="insertTopicInfo.uuid"/>
        <select name="competency" v-model="insertTopicInfo.competency">
          <option value="BEGINNER"> BEGINNER </option>
          <option value="NOVICE"> NOVICE </option>
          <option value="INTERMEDIATE"> INTERMEDIATE </option>
          <option value="EXPERT"> EXPERT </option>
          <option value="MASTER"> MASTER </option>
        </select>
        <input type="text" placeholder="subjectID" v-model="insertTopicInfo.subjectID"/>
        <button @click="createTopic"> Submit Subject </button>
      </div>

      <div>
        <h3> Print Store </h3>
        <button @click="printStore"> Submit </button>
      </div>

      <div> 
        <h3> Create Study Sessions </h3>
        <button @click="createStudySessions"> Submit </button>
      </div>
    </form>


  </div> 
</template>

<script setup>
import { supabase } from '@/clients/supabase';
import { reactive, ref } from 'vue';
import { useAccountStore } from '@/stores/account';

const account = useAccountStore();

let userInfo = reactive({
  email: "",
  password: "",
});

let insertAccountInfo = reactive({
  email: "bdriscoll407@gmail.com",
  password: "Imcool123!",
  name: "Brian"
});

const insertSubjectInfo = reactive({
  name: "History",
  uuid: account.uuid
});

const insertTopicInfo = reactive({
  name: "Revolutionary War",
  competency: "NOVICE",
  uuid: account.uuid,
  subjectID: 1
}); 

let userName = ref("");

const insertAccount = async () => {

  const response = await fetch("/api/users/create", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      uuid: "4584be96-8ce5-4789-84e4-c87b037ba320",
      email: insertAccountInfo.email,
      name: insertAccountInfo.name
    })
  });

  if (!response.ok)
  {
    console.error("Failed to create user " + response)
  }
  else
  {
    console.log("User created successfully!");
  }
}

async function createAccount()
{

  const { data, error } = await supabase.auth.signUp({
    email: userInfo.email,
    password: userInfo.password,
    name: ''
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
    account.status = true;
    account.name = data.user.email;
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
    account.status = false;
    account.name = '';
  }
}

async function insertName()
{
  console.log(userName.value);
  try 
  {
    const response = await fetch(`/api/users/${userName.value}/1`);
    console.log(response);
  }
  catch (err)
  {
    console.log(err);
  }
}

async function createSubject()
{
  try
  {
    const response = await fetch("/api/subjects/create");

    if (!response.ok)
    {
      console.error("ACCOUNT MANAGER CONSOLE: Failed to create subject - ")
    }
    else
    {
      console.log("ACCOUNT MANAGER CONSOLE: Subject created successfully!");
    }
  }
  catch (error)
  {
    console.error("ACCOUNT MANAGER CONSOLE: Create subject failed - " + error);
  }
}

async function createTopic()
{
  try 
  {
    const response = await fetch("/api/topics/create", {
      method: "POST",
      headers:
      {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: insertTopicInfo.name,
        uuid: insertTopicInfo.uuid,
        competency: insertTopicInfo.competency,
        subjectID: insertTopicInfo.subjectID
      })
    });

    if (!response.ok)
    {
      console.error("ACCOUNT MANAGER CONSOLE: Failed to create topic - ")
    }
    else
    {
      console.log("ACCOUNT MANAGER CONSOLE: Topic created successfully!");
    }
  }
  catch (error)
  {
    console.error("ACCOUNT MANAGER CONSOLE: create topics failed " + error);
  }


}

const printStore = () => 
{
  console.log(account.uuid);
}

const createStudySessions = async () =>
{

  let topicID = 1;
  let sessionCount = 5;

  try 
  {
    const response = await fetch(`/api/studySessions/generate?topicID=${topicID}&sessionCount=${sessionCount}`, {
      method: "GET",
      headers:
      {
        "Content-Type": "application/json"
      },
    });

    if (!response.ok)
    {
      console.error(`Failed to create study sessions. Status: ${response.status}`);
    }
    else
    {
      const data = await response.text();
      console.log("Response:", data);
    }

  }
  catch (error)
  {
    console.error("ACCOUNT MANAGER CONOLE: " + error);
  }

}

</script>