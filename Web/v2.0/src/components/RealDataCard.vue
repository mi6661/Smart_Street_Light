<template>
  <div>
    <div ref="container" class="main"></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Chart } from '@antv/g2';
import {getSensorDataListNow} from "../api/lightApi.js";

const location = '呈贡区一路1号'
const temp_data = ref();
const humid_data = ref();
const speed_data = ref();

// 1. 声明一个 ref，并将其与模板中的 div 绑定
const container = ref(null);
let chart;



const make_chart = ()=>{
  // 2. 确保 DOM 元素已经挂载
  if (container.value) {
    // 3. 在 onMounted 内部创建 Chart 实例，使用 ref 的值作为容器
    chart = new Chart({
      container: container.value
    });

    // // 4. 将所有与 DOM 相关的操作移动到这里
    // const button = document.createElement('button');
    // button.innerText = 'Update Data';
    // button.style.display = 'block';
    // container.value.appendChild(button); // 注意这里是 container.value
    //添加标题
    const topic = document.createElement('div')
    topic.textContent = location
    topic.style.width = '100%';
    topic.style.height = '20px';
    container.value.appendChild(topic);

    const view = chart
        .facetRect()
        .data([
          { name: '温度', percent: 27, color: 'rgba(90, 132, 226, 1)' },
          { name: '湿度', percent: 81, color: 'rgba(250, 57, 57, 1)' },
          { name: '风速', percent: 68, color: 'rgba(253, 192, 45, 1)' },
        ])
        .encode('x', 'name')
        .axis(false)
        .legend(false)
        .view().style('height', '100px')
        .attr('frame', false)
        .coordinate({ type: 'radial', innerRadius: 0.7, outerRadius: 0.95 });

    //容器环
    view
        .interval()
        .encode('y', 100)//环最大值
        .encode('size', 52)//外环粗细
        .scale('y', { zero: true })
        .axis(false)
        .style('fill', 'rgba(232, 232, 232, 1)')
        .animate(false);
    //变量环
    view
        .interval()
        .encode('y', 'percent')
        .encode('color', 'color')
        .encode('size', 80)//内环粗细
        .scale('color', { type: 'identity' })
        .tooltip({ name: '已使用', channel: 'y' })
        .axis(false)
        .style('radius', 26)
        .style('shadowColor', 'color')
        .style('shadowBlur', 10)
        .style('shadowOffsetX', -1)
        .style('shadowOffsetY', -1)
        .animate('enter', { type: 'waveIn', duration: 1000 });
    //文字图层
    view
        .text()
        .encode('text', (d) => `${d.name} ${d.percent}%`)
        .style('textAlign', 'center')
        .style('textBaseline', 'middle')
        .style('fontSize', 15)
        .style('color', 'rgba(74, 74, 74, 1)')
        .style('x', '50%')
        .style('y', '50%')
        .tooltip(false);


    chart.render();
    chart.changeSize(300,100)
  }
}
const load_data = () => {
  getSensorDataListNow().then(data => {
    temp_data.value = data;
  })
}
onMounted(() => {
  load_data();


  //绘制图表
  make_chart()
});
</script>

<style scoped>

</style>