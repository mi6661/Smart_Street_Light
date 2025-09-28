<template>
    <div class="card-body">
        <RealtimeCard v-for="item in realTimeData"
                      :location=item.location
                      :temperature=item.temperature
                      :humidity=item.humidity
                      :windSpeed=item.windSpeed>
        </RealtimeCard>
    </div>
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
.card-body{
    width: 100%;
    height: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
</style>