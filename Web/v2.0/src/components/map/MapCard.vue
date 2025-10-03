<template>
    <h1>地图展示</h1>
    </template>

<script setup>
import {onMounted, ref} from "vue";



const locations = [
    {
        latitude: 116.481485,
        longitude: 39.990464
    },
    {
        latitude: 116.491185,
        longitude: 39.950464
    }
]

const options =ref()


onMounted(() => {
    options.value = getDefaultStyleMapURL(locations);
    console.log(options.value);
})
</script>

<style scoped>

</style>