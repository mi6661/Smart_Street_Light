<template>
  <div>
    <h1>台灯控制面板</h1>
    <div class="light-list-container">
      <LightCard
          v-for="light in lights"
          :key="light.id"
          :light="light"
          @update-light="handleUpdateLight"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import LightCard from '../components/LightCard.vue';
import { getLightList, updateLightStatus } from '../api/lightApi';

const lights = ref([]);

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

const handleUpdateLight = async (updatedLight) => {
  try {
    const response = await updateLightStatus(updatedLight);

    if (response && response.status === 200) {
      const index = lights.value.findIndex(light => light._id === updatedLight.id);
      if (index !== -1) {
        lights.value[index] = { ...updatedLight, _id: updatedLight.id };
      }
      console.log('路灯状态更新成功');
    } else {
      console.error('API返回更新失败:', response);
    }
  } catch (error) {
    console.error('更新路灯状态失败:', error);
  }
};

onMounted(() => {
  fetchLights();
});
</script>

<style scoped>
.light-list-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
</style>