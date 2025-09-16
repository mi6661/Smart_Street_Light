<template>
  <div id="app">
    <h1>台灯控制面板</h1>
    <div class="light-list-container">
      <LightCard
          v-for="light in lights"
          :key="light._id"
          :light="light"
          @update-light="handleUpdateLight"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import LightCard from './components/LightCard.vue';
import { getLightList, updateLightStatus } from './api/lightApi';

const lights = ref([]);

// 获取路灯列表
const fetchLights = async () => {
  try {
    const data = await getLightList();
    if (Array.isArray(data)) {
      lights.value = data;
    } else {
      console.error('API返回的数据不是数组:', data);
    }
  } catch (error) {
    // 错误处理已在 lightApi.js 中
  }
};

// 处理路灯状态更新
const handleUpdateLight = async (updatedLight) => {
  try {
    // 调用 API 更新路灯状态
    const response = await updateLightStatus(updatedLight);

    if (response && response.status === 200) {
      // API更新成功后，更新本地状态，确保界面同步
      const index = lights.value.findIndex(light => light._id === updatedLight._id);
      if (index !== -1) {
        lights.value[index] = { ...updatedLight };
      }
      console.log('路灯状态更新成功');
    } else {
      console.error('API返回更新失败:', response);
    }
  } catch (error) {
    console.error('更新路灯状态失败:', error);
  }
};

// 组件挂载后立即获取数据
onMounted(() => {
  fetchLights();
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.light-list-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
</style>