<template>
    <tr>
        <td> 1 </td>
        <td class="inputField"> <input /> </td>
        <td class="inputField"> <input /> </td>
        <td> <button class="editButton"> Submit </button> </td>
        <td> <button class="editButton"> AI Generate </button> </td>
    </tr>
</template>

<script setup>
 

</script>

<style scoped>

input 
{
    width: 100%;
}

th, td {
    padding: 12px;
    text-align: left;
    border: 1px solid #dbd6c6;
}

th {
    background-color: #fab22b;
    color: rgb(46, 46, 46);
}

tr:nth-child(even) {
    background-color: #ebe5d4;
}

tr:nth-child(odd) {
    background-color: #86fff5;
}

.editButton
{
    height: 100%;
    width: 100%;
}

</style>