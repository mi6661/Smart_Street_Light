<template>
    <div>
        <div ref="container"></div>
    </div>
</template>


<script setup>
import {defineProps, onMounted, watch , ref} from "vue";
import { Chart } from '@antv/g2';

const props = defineProps({
    choice: {
        type: String,
        required: true,
    }
})

watch(()=>props.choice, (newVal,oldVal)=>{
    console.log(newVal)
},{immediate: true})



//标签容器
const container = ref(null);
// 准备数据
const data = [
    { year: '1991', value: 15468 },
    { year: '1992', value: 16100 },
    { year: '1993', value: 15900 },
    { year: '1994', value: 17409 },
    { year: '1995', value: 17000 },
    { year: '1996', value: 31056 },
    { year: '1997', value: 31982 },
    { year: '1998', value: 32040 },
    { year: '1999', value: 33233 },
];

const monthlyTemperatureData = [
    { day: '1日', temp: 18 },
    { day: '2日', temp: 19 },
    { day: '3日', temp: 20 },
    { day: '4日', temp: 21 },
    { day: '5日', temp: 22 },
    { day: '6日', temp: 21 },
    { day: '7日', temp: 20 },
    { day: '8日', temp: 22 },
    { day: '9日', temp: 23 },
    { day: '10日', temp: 24 },
    { day: '11日', temp: 25 },
    { day: '12日', temp: 26 },
    { day: '13日', temp: 27 },
    { day: '14日', temp: 28 },
    { day: '15日', temp: 29 },
    { day: '16日', temp: 28 },
    { day: '17日', temp: 27 },
    { day: '18日', temp: 26 },
    { day: '19日', temp: 25 },
    { day: '20日', temp: 24 },
    { day: '21日', temp: 23 },
    { day: '22日', temp: 22 },
    { day: '23日', temp: 21 },
    { day: '24日', temp: 20 },
    { day: '25日', temp: 19 },
    { day: '26日', temp: 18 },
    { day: '27日', temp: 17 },
    { day: '28日', temp: 16 },
    { day: '29日', temp: 15 },
    { day: '30日', temp: 14 }
];

//图标初始化
function renderBarChart(container) {
    const chart = new Chart({
        container,
        autoFit: true,
    });


    // 出入数据
    chart.data(monthlyTemperatureData) // 绑定数据

    chart
        .area()
        .encode('x', (d) => d.day)
        .encode('y', 'temp')
        .encode('shape', 'area') // 'area', 'smooth', 'hvh', 'vh', 'hv'
        .style('opacity', 0.2)
        .axis('y', { labelFormatter: '~s', title: false })
        .animate('update', { duration: 300 }); // 指定更新动画的时间

    // 渲染可视化
    chart.render();

    return chart;
}

onMounted(() => {
    renderBarChart(container.value);
})


</script>



<style scoped>

</style>