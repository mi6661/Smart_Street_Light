<template>
    <div class="card-body-outer">
        <div class="card-body-inner">
            <RealtimeCard v-for="item in realTimeData"
                          :location=item.location
                          :temperature=item.temperature
                          :humidity=item.humidity
                          :windSpeed=item.windSpeed>
            </RealtimeCard>
        </div>
    </div>

</template>

<script setup>
import {onMounted, ref, watch} from 'vue';
import RealtimeCard from "../../../components/data/RealDataCard.vue";
import {getAllSensorsRealTimeData} from "../../../api/sensorApi.js";


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
.card-body-outer {
    width: 100%;
}
.card-body-inner{
    display: grid;
    grid-template-columns: repeat(auto-fill, 320px);
    gap: 16px;
    justify-content: center;
}
</style>