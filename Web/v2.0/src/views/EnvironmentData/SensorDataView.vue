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

let chart;
const container = ref(null);

//图标初始化
function renderBarChart(container) {
    const chart = new Chart({
        container,
    });

    // 准备数据
    const data = [
        { genre: 'Sports', sold: 275 },
        { genre: 'Strategy', sold: 115 },
        { genre: 'Action', sold: 120 },
        { genre: 'Shooter', sold: 350 },
        { genre: 'Other', sold: 150 },
    ];

    // 声明可视化
    chart
        .interval() // 创建一个 Interval 标记
        .data(data) // 绑定数据
        .encode('x', 'genre') // 编码 x 通道
        .encode('y', 'sold') // 编码 y 通道
        .encode('key', 'genre') // 指定 key
        .animate('update', { duration: 300 }); // 指定更新动画的时间

    // 渲染可视化
    chart.render();

    return chart;
}

onMounted(() => {
    chart = renderBarChart(container.value);
})


</script>



<style scoped>

</style>