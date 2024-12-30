<template>
    <tr>
        <td class="tableWrapper"> <input class="referenceInput"/> </td>
        <td class="tableWrapper"> <input class="referenceInput"/> </td>
        <td id="submitBox"> <button id="submitButton"> submit </button> </td>
    </tr>

</template>

<script setup>
</script>

<style scoped>
#submitBox 
{
    border: none;
}

#submitButton
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
</style>