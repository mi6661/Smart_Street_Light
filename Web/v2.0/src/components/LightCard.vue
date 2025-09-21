<template>
  <div class="light-card" @click="goToDetails">
    <h3>{{ light.location }}</h3>
    <p>ID: {{ light.id }}</p>
    <div class="status-indicator" :class="{ 'on': light.status === 'on', 'off': light.status === 'off' }">
      状态: {{ light.status === 'on' ? '开启' : '关闭' }}
    </div>

    <button @click.stop="toggleStatus" class="control-button">
      {{ light.status === 'on' ? '关闭' : '开启' }}
    </button>

    <div class="brightness-control" @click.stop>
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

    <div class="auto-mode" @click.stop>
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
import { useRouter } from 'vue-router';

/*定义组件需要的数据*/
const props = defineProps({
  light: {
    type: Object,
    required: true
  }
});
/*组件可触发事件*/
const emit = defineEmits(['update-light']);
const router = useRouter();

const localBrightness = ref(props.light.brightness);
const localAuto = ref(props.light.auto === 'on');

/*数据监听函数*/
watch(() => props.light.brightness, (newVal) => {
  localBrightness.value = newVal;
});
watch(() => props.light.auto, (newVal) => {
  localAuto.value = newVal === 'on';
});

const sendUpdate = (updates) => {
  const payload = {
    id: props.light.id,
    location: props.light.location,
    status: props.light.status,
    brightness: props.light.brightness,
    auto: props.light.auto,
    ...updates
  };
  //向父组件触发一个名为update-light的自定义事件，并出入参数payload
  emit('update-light', payload);
};

/*切换路灯的开光状态*/
const toggleStatus = () => {
  const newStatus = props.light.status === 'on' ? 'off' : 'on';
  sendUpdate({ status: newStatus });
};

/*更新路灯的亮度*/
const updateBrightness = () => {
  sendUpdate({ brightness: localBrightness.value });
};

/*切换路灯的自动模式*/
const toggleAuto = () => {
  const newAuto = localAuto.value ? 'on' : 'off';
  sendUpdate({ auto: newAuto });
};

/*跳转到详细面板*/
const goToDetails = () => {
  router.push({ name: 'LightDetails', params: { id: props.light.id } });
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
  cursor: pointer;
  transition: transform 0.2s;
}
.light-card:hover {
  transform: scale(1.03);
}

.status-indicator {
  display: inline-block;
  padding: 4px 8px;
  border-radius: 4px;
  color: white;
  margin-bottom: 10px;
}
.status-indicator.on { background-color: #4CAF50; }
.status-indicator.off { background-color: #f44336; }

.control-button {
  background-color: #007BFF;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
}
.brightness-control, .auto-mode { margin-top: 10px; }
input[type="range"] {
  width: 150px;
  margin: 0 8px;
}
</style>