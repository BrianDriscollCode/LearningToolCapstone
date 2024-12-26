<template>
  <span class="dashBoardLink" @click="goToDashboard"> dashboard </span>
  <span> / schedule </span> 
    <section class="rescheduleContainer">
        <div class="rescheduleWrapper">
            <h1> Reschedule </h1>
            <RescheduleComponent :studySessionID="id" :name="name" />
        </div>
    </section>
</template>

<script setup>
import RescheduleComponent from '@/components/UserPlatform/RescheduleComponent.vue';
import { defineProps } from "vue";
import { useRouter } from 'vue-router';

defineProps({
  id: {
    type: String,
    required: true
  },
  name: {
    type: String,
    required: true
  },
});

const router = useRouter();

const goToDashboard = () =>
{
  router.push('/platform/dashboard');
}

</script>

<style scoped>
.rescheduleContainer
{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
}

.rescheduleWrapper
{
    display: flex;
    background-color: aqua;
    width: 500px;
    padding: 1em;
    justify-content: center;
    flex-direction: column;
    text-align: center;
    margin-bottom: 10em;
    border-radius: 1em;
}

.dashBoardLink 
{
    color: rgb(86, 133, 221);
}

.dashBoardLink:hover
{
    cursor: pointer;
    color: rgb(245, 159, 1);
}
</style>