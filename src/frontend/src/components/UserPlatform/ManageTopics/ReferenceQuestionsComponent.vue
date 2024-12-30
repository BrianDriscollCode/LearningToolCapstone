<template>
    <p> Reference Questions </p>
    <div class="referenceQuestionsContainer">
       <table>
            <thead>
                <th> # </th>
                <th> Question </th>
                <th> Answer </th>
                <th> Action </th>
            </thead>
            <tbody>
                <tr>
                    <td> 1</td>
                    <td class="inputField"> <input /> </td>
                    <td class="inputField"> <input /> </td>
                    <td> <button class="editButton"> Edit </button> </td>
                    <td> <button class="editButton"> AI Generate </button> </td>
                </tr>
            </tbody>
       </table>
    </div>
    
</template>

<script setup>

</script>

<style scoped>
input {
    width: 100%;
}

.inputField
{

}


.editButton
{
    height: 100%;
    width: 100%;
}

</style>