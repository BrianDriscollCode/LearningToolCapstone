<template>
    <section class="rescheduleContainer">
        <div class="rescheduleWrapper">
            <h1> Reschedule </h1>
            <RescheduleComponent />
        </div>
    </section>
</template>

<script setup>
import RescheduleComponent from '@/components/UserPlatform/RescheduleComponent.vue';

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
</style>