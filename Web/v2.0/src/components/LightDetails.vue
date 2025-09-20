<template>
  <div class="light-details-container">
    <button @click="goBack" class="back-button">返回</button>
    <div v-if="loading" class="loading">加载中...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="data-display">
      <h2>路灯 {{ id }} 的传感器数据</h2>
      <div class="data-list">
        <div v-for="data in sensorData" :key="data.id" class="data-item">
          <p><strong>时间:</strong> {{ formatDate(data.create_time) }}</p>
          <p><strong>温度:</strong> {{ data.temperature }} °C</p>
          <p><strong>湿度:</strong> {{ data.humidity }} %</p>
          <p><strong>风速:</strong> {{ data.wind_speed }} %</p>
          <p><strong>PM2.5:</strong> {{ data.pm25 !== -1 ? data.pm25 : '无数据' }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

// 通过 props 接收路由参数
const props = defineProps({
  id: {
    type: String, // 路由参数是字符串，需要注意
    required: true
  }
});

const router = useRouter();
const sensorData = ref([]);
const loading = ref(true);
const error = ref(null);

// 获取传感器数据
const fetchSensorData = async () => {
  try {
    //查询10条记录
    const response = await axios.get(`http://localhost:8080/sensor/list?id=${props.id}&amount=10`);
    if (response.data && response.status === 200) {
      sensorData.value = response.data;
      if (!sensorData.value || sensorData.value.length === 0) {
        error.value = '未找到相关传感器数据。';
      }
    } else {
      console.log(response);
      error.value = '获取数据失败。';
    }
  } catch (err) {
    console.error(err);
    error.value = '请求出错，请检查网络或后端接口。';
  } finally {
    loading.value = false;
  }
};

const formatDate = (dateString) => {
  const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
  return new Date(dateString).toLocaleDateString('zh-CN', options);
};

const goBack = () => {
  router.back();
};

onMounted(() => {
  fetchSensorData();
});
</script>

<style scoped>
.light-details-container {
  padding: 20px;
  max-width: 800px;
  margin: auto;
  font-family: Arial, sans-serif;
}
.back-button {
  background-color: #007BFF;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 20px;
}
.loading, .error {
  text-align: center;
  font-size: 1.2em;
  color: #555;
}
.data-display {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}
.data-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}
.data-item {
  border-bottom: 1px solid #eee;
  padding-bottom: 15px;
}
</style>