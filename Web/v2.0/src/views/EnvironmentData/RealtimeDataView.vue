<template>
  <h1>实时监测面板</h1>

  <div v-for="item in temp_data">{{item.id}} : {{item.temperature}}</div>


</template>

<script setup>
import {onMounted, ref, watch} from 'vue';
import {getSensorDataListNow} from "../../api/lightApi.js";


//实时温度数据
const temp_data = ref();







/*请求温度数据函数*/
const loadTempData = ()=>{
  getSensorDataListNow().then((data)=>{
    temp_data.value = data;
    console.log(temp_data.value)
    console.log(data)
  });

}

onMounted(() => {
  loadTempData();
})


</script>

<style scoped>

</style>