<template>

    <div class="statisticsContainer">
        <h3> Your Stats: </h3>
        <p> Hours Studied: 0 </p>
        <p> Study Sessions completed: 0</p>
        <p> Competency Improved: 0 </p>
    </div>

</template>

<script setup>


</script>

<style scoped>
.statisticsContainer {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px #4e4e4e;
    border: 1px solid #e0e0e0;
    width: 300px;
    margin: 20px auto;
}

.statisticsContainer h3 {
    font-size: 1.25em;
    font-weight: 600;
    color: #333;
    margin-bottom: 1em;
    margin-top: 1em;
}

.statisticsContainer p {
    font-size: 1rem;
    color: #666;
    margin: 5px 0;
}

.statisticsContainer p:first-child {
    font-weight: 500;
}
</style>