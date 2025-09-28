<template>

    <RealtimeCard v-for="item in realTimeData"
        :temperature=item.temperature
        :humidity=item.humidity
        :speed=item.windSpeed>
    </RealtimeCard>


</template>

<script setup>
import {onMounted, ref, watch} from 'vue';
import RealtimeCard from "../../components/RealDataCard.vue";
import {getAllSensorsRealTimeData} from "../../api/sensorApi.js";


//实时温度数据
const realTimeData = ref();







/*请求温度数据函数*/
const loadTempData = ()=>{

  getAllSensorsRealTimeData().then((data)=>{
      realTimeData.value = data;
      console.log(realTimeData.value)
  })

}

onMounted(() => {
    loadTempData();
})


</script>

<style scoped>

</style>