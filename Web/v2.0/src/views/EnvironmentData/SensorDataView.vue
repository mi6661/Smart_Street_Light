<template>
    <div>
        <div ref="container"></div>
    </div>
</template>


<script setup>
import {defineProps, onMounted, watch , ref} from "vue";
import { Chart } from '@antv/g2';
import {getSensorDataListNow} from "../../api/lightApi.js";

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
const tempData = ref();

watch(tempData, (newVal,oldVal)=>{



    //绘制图表
    //renderBarChart(container.value,newVal);

},{immediate: true})


//图标初始化
function renderBarChart(container,tp) {
    const chart = new Chart({
        container,
        autoFit: true,
    });


    // 出入数据


    chart
        .interval()
        .data(tp) // 绑定数据
        .encode('x', (d) => "hello world this is a id"+d.id)
        .encode('y', 'temperature')
/*        .encode('shape', 'area') // 'area', 'smooth', 'hvh', 'vh', 'hv'
        .style('opacity', 0.2)
        .axis('y', { labelFormatter: '~s', title: false })
        .animate('update', { duration: 300 }); // 指定更新动画的时间*/

    // 渲染可视化
    chart.render();

    return chart;
}

onMounted(() => {
    //从后端获取数据呀
    const my_promise = getSensorDataListNow();
    my_promise.then((result) => {
        renderBarChart(container.value, result);
    })

})




</script>



<style scoped>

</style>