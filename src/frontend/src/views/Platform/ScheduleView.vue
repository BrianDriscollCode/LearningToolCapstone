<template>
    <div>
        <span class="dashBoardLink" @click="goToDashboard"> back to dashboard... </span>
    </div>
    <ScheduleComponent />
</template>

<script setup>
import { useRouter } from "vue-router"
import ScheduleComponent from "@/components/UserPlatform/ScheduleComponent.vue"

const router = useRouter();

const goToDashboard = () =>
{
    router.push("/platform/dashboard");
}

</script>

<style scoped>
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