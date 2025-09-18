<template>
  <div class="light-card" @click="goToDetails">
  </div>
</template>

<script setup>
import { defineProps, defineEmits, ref, watch } from 'vue';
import { useRouter } from 'vue-router'; // 导入 useRouter

const props = defineProps({
  light: {
    type: Object,
    required: true
  }
});

const emit = defineEmits(['update-light']);
const router = useRouter(); // 获取 router 实例

// ... 其他代码保持不变 ...

// 新增方法：导航到详情页
const goToDetails = () => {
  // 使用 router.push 导航到 '/details/:id' 路径
  // 注意这里的 light._id 是唯一的标识符
  router.push({ name: 'LightDetails', params: { id: props.light._id } });
};
</script>

<style scoped>
.light-card {
  /* ... 保持不变 ... */
  cursor: pointer; /* 添加这个以表示可点击 */
  transition: transform 0.2s; /* 添加过渡效果 */
}
.light-card:hover {
  transform: scale(1.03); /* 鼠标悬停时放大 */
}
/* ... 其他样式保持不变 ... */
</style>