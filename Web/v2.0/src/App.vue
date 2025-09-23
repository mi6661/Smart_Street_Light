<template>
  <div id="app">
    <!--<router-view></router-view>-->
    <RealDataCard></RealDataCard>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
<script setup lang="ts">
import RealDataCard from "./components/RealDataCard.vue";
</script>