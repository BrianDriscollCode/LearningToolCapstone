<template>
    <section class="pageContainer">
        <SignUpForm v-show="formStatus.signUpForm" @signUpComplete="signUpComplete" />
        <CheckEmail v-show="formStatus.checkEmail" />
    </section>
</template>

<script setup>
import SignUpForm from '@/components/AccountManagement/SignUpForm.vue';
import CheckEmail from '@/components/AccountManagement/CheckEmail.vue';
import { reactive } from 'vue';


const formStatus = reactive({
    signUpForm: true,
    checkEmail: false
})

const signUpComplete = () => 
{


    formStatus.signUpForm = false;
    formStatus.checkEmail = true;
}

</script>

<style scoped>
.pageContainer
{
    flex: 1;
    height: 100%;
    width: 100%;
    background-color: rgb(77, 77, 77);
    display: flex;
    justify-content: center;
    overflow: hidden;
}
</style>