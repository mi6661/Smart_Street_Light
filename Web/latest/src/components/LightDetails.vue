<template>
  <div class="light-details-container">
    <button @click="goBack" class="back-button">返回</button>
    <div v-if="loading" class="loading">加载中...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="data-display">
      <h2>路灯 {{ id }} 的传感器数据</h2>
      <div class="data-list">
        <div v-for="data in sensorData" :key="data._id" class="data-item">
          <p><strong>时间:</strong> {{ formatDate(data.create_time) }}</p>
          <p><strong>温度:</strong> {{ data.temperature }} °C</p>
          <p><strong>湿度:</strong> {{ data.humidity }} %</p>
          <p><strong>PM2.5:</strong> {{ data.pm24 !== -1 ? data.pm24 : '无数据' }}</p>
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
    const response = await axios.post(`http://localhost:8080/sensor/id?id=${props.id}`);
    if (response.data && response.data.status === 200) {
      sensorData.value = response.data.data;
      if (!sensorData.value || sensorData.value.length === 0) {
        error.value = '未找到相关传感器数据。';
      }
    } else {
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
---
## 5. 修改 `LightCard.vue`
在 `LightCard.vue` 中，我们需要将卡片变成可点击的，并使用 `router.push` 方法导航到详情页。

**src/components/LightCard.vue (修改后)**
```vue

---
## 6. 修改 `App.vue`
`App.vue` 需要移除 `LightCard` 组件，并用 `router-view` 替换。`router-view` 是 Vue Router 提供的组件，用于显示当前路由匹配到的组件。

**src/App.vue (修改后)**
```vue
<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script setup>
// 这部分代码移动到 router/index.js 和新的组件中
</script>

<style>
/* ... 保持不变 ... */
</style>