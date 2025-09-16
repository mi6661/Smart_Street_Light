<template>
  <div class="light-card">
    <h3>{{ light.location }}</h3>
    <p>ID: {{ light._id }}</p>
    <div class="status-indicator" :class="{ 'on': light.status === 'on', 'off': light.status === 'off' }">
      状态: {{ light.status === 'on' ? '开启' : '关闭' }}
    </div>

    <button @click="toggleStatus" class="control-button">
      {{ light.status === 'on' ? '关闭' : '开启' }}
    </button>

    <div class="brightness-control">
      <label>亮度:</label>
      <input
          type="range"
          min="0"
          max="100"
          v-model.number="localBrightness"
          @mouseup="updateBrightness"
      />
      <span>{{ localBrightness }}%</span>
    </div>

    <div class="auto-mode">
      <label>自动模式:</label>
      <input
          type="checkbox"
          v-model="localAuto"
          @change="toggleAuto"
      />
      <span>{{ localAuto ? '开启' : '关闭' }}</span>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits, ref, watch } from 'vue';

const props = defineProps({
  light: {
    type: Object,
    required: true
  }
});

const emit = defineEmits(['update-light']);

// 使用 ref 创建本地状态，避免直接修改 props
const localBrightness = ref(props.light.brightness);
const localAuto = ref(props.light.auto === 'on');

// 监听 props 变化，同步本地状态
watch(() => props.light.brightness, (newVal) => {
  localBrightness.value = newVal;
});
watch(() => props.light.auto, (newVal) => {
  localAuto.value = newVal === 'on';
});

// 切换开关状态
const toggleStatus = () => {
  const newStatus = props.light.status === 'on' ? 'off' : 'on';
  emit('update-light', { ...props.light, status: newStatus });
};

// 更新亮度
const updateBrightness = () => {
  emit('update-light', { ...props.light, brightness: localBrightness.value });
};

// 切换自动模式
const toggleAuto = () => {
  const newAuto = localAuto.value ? 'on' : 'off';
  emit('update-light', { ...props.light, auto: newAuto });
};
</script>

<style scoped>
.light-card {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 16px;
  margin: 10px;
  width: 280px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
}

.status-indicator {
  display: inline-block;
  padding: 4px 8px;
  border-radius: 4px;
  color: white;
  margin-bottom: 10px;
}

.status-indicator.on {
  background-color: #4CAF50;
}

.status-indicator.off {
  background-color: #f44336;
}

.control-button {
  background-color: #007BFF;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
}

.brightness-control, .auto-mode {
  margin-top: 10px;
}

input[type="range"] {
  width: 150px;
  margin: 0 8px;
}
</style>