<!--
路灯分布地图页面
-->
<template>
    <h1>路灯分布地图页面</h1>

    <!--路灯分布页面-->
    <a-layout style="padding: 24px 0; background: #fff">
        <!--侧边栏-->
        <a-layout-sider width="200" style="background: #fff">

        </a-layout-sider>
        <!--内容区域-->
        <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
            <ElAmap></ElAmap>
        </a-layout-content>
    </a-layout>

</template>

<script setup lang="ts">
import MapCard from "../../components/map/MapCard.vue";
import ElAmap from "../../components/map/AmapCard.vue";



</script>


<style scoped>

</style>