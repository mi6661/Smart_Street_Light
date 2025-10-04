<template>
    <div class="container">
        <div><h1>{{props.title}}</h1></div>
        <div ref="chart" class="echarts"></div>
    </div>

</template>


<script setup>
import * as echarts from 'echarts'
import {onMounted, ref} from "vue";

/*父组件传入数据*/
const props = defineProps({
    title:{
        type: String,
        required: true
    },
    data: {
        type: Array,
        required: true
    }
});


/*折线图*/
const chart = ref();
/*数据*/
const options = ref();
/*基本数据格式*/
options.value = {
    xAxis: {
        type: 'category',
        data: []
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            data: [],
            type: 'line'
        }
    ]
}

onMounted(() => {
    const myChart = echarts.init(chart.value);
    props.data.forEach((item) => {
        console.log(item.day);
        console.log(item.val);
        options.value.xAxis.data.push(item.day);
        options.value.series[0].data.push(item.val);
    })
    myChart.setOption(options.value);
    window.addEventListener('resize', () => {
        myChart.resize();
    },false);
})

</script>

<style scoped>
.container{
    width: 100%;
    height: 300px;
}
.echarts {
    width: 100%;
    height: 100%;
}
</style>