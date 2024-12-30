<template>
    <td class="tableWrapper"> <input class="referenceInput"/> </td>
    <td class="tableWrapper"> <input class="referenceInput"/> </td>
    <td id="submitBox"> <button> submit </button> </td>
</template>

<script setup>
</script>

<style scoped>
#submitBox 
{
    border: none;
}

button
{
    width: 100%;
    padding: 1em;
}

.tableWrapper
{
text-align: center;
}

.referenceInput
{
    width: 80%;
}
</style>