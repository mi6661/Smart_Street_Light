<template>
    <div class="chart-container">
        <!--<h1>气温变化</h1>-->
        <div id="chart-panel"></div>
        <LineChart :data="temp_data" title="平均气温变化" v-if="loaded"></LineChart>
        <LineChart :data="humi_data" title="平均湿度变化" v-if="loaded"></LineChart>
        <LineChart :data="speed_data" title="平均风速变化" v-if="loaded"></LineChart>

        <div v-else>
            <p>数据加载中。。。</p>
        </div>
    </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {getSensorsAnalysisData} from "../../../api/sensorApi.js";
import LineChart from "../../../components/data/charts/LineChart.vue";

const loaded = ref(false);

/*传感器数据*/
const temp_data = ref([]);
const humi_data = ref([]);
const speed_data = ref([]);

onMounted(() => {
    /*请求传感器数据*/
    getSensorsAnalysisData().then(data => {
        data.forEach((item) => {
            console.log(item);
            temp_data.value.push({
                day: item.day,
                val: item.temp,
            });
            humi_data.value.push({
                day: item.day,
                val: item.humi,
            });
            speed_data.value.push({
               day: item.day,
                val: item.windspeed,
            });
        })
        loaded.value = true;
    });

});


</script>

<style scoped>
.chart-container {
    width: 100%;
    height: 100%;
}
</style>
