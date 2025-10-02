<template>
    <div class="sensor-card" v-motion-fade>
<!--        <h2 class="title">传感器数据</h2>-->
        <div class="item" v-motion-pop>
            <span class="label">📍 位置：</span>
            <span class="value">{{ location }}</span>
        </div>
        <div class="item" v-motion-pop>
            <span class="label">🌡 温度：</span>
            <span class="value">{{ temperature }} ℃</span>
        </div>
        <div class="item" v-motion-pop>
            <span class="label">💧 湿度：</span>
            <span class="value">{{ humidity }} %</span>
        </div>
        <div class="item" v-motion-pop>
            <span class="label">💨 风速：</span>
            <span class="value">{{ windSpeed }} m/s</span>
        </div>
    </div>
</template>

<script setup>
import { MotionPlugin } from '@vueuse/motion'

// 父组件要在 main.js 里 use(MotionPlugin)
defineProps({
    temperature: { type: Number, required: true },
    humidity: { type: Number, required: true },
    windSpeed: { type: Number, required: true },
    location: { type: String, required: true }
})
</script>

<style scoped>
.sensor-card {
    max-width: 320px;
    margin: 16px auto;
    padding: 20px;
    border-radius: 16px;
    background: linear-gradient(135deg, #e0f7fa, #e8f5e9);
    box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    font-family: "Segoe UI", Arial, sans-serif;
    transition: transform 0.2s ease-in-out;
}
.sensor-card:hover {
    transform: translateY(-5px) scale(1.02);
}
.title {
    margin-bottom: 14px;
    font-size: 20px;
    font-weight: bold;
    color: #2c3e50;
    text-align: center;
}
.item {
    margin: 8px 0;
    font-size: 16px;
    display: flex;
    justify-content: space-between;
}
.label {
    font-weight: 600;
    color: #34495e;
}
.value {
    color: #16a085;
    font-weight: 500;
}
</style>

