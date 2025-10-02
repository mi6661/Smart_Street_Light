<template>
    <a-layout>
        <!-- 顶部标签栏 -->
        <a-layout-header class="header">
            <div class="logo" />
            <a-menu v-model:selectedKeys="selectedKeys1" @click="menuClick" theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }" >
                <a-menu-item key="DataView">数据查看</a-menu-item>
                <a-menu-item key="MapView">路灯分布</a-menu-item>
            </a-menu>
        </a-layout-header>
        <a-layout-content style="padding: 0 50px">
            <!-- 路径显示栏 -->
            <!--
            <a-breadcrumb style="margin: 16px 0">
                <a-breadcrumb-item>Home</a-breadcrumb-item>
                <a-breadcrumb-item>List</a-breadcrumb-item>
                <a-breadcrumb-item>App</a-breadcrumb-item>
            </a-breadcrumb>
            -->

            <router-view></router-view>


            
        </a-layout-content>
        
        
        
        <!--底部区域-->
        <a-layout-footer style="text-align: center">
            Gwen Design ©2025 Created by Gwen
        </a-layout-footer>
    </a-layout>
</template>


<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import {useRouter} from "vue-router";
const router = useRouter();

const selectedKeys1 = ref<string[]>(['1']);


/*顶栏选择显示参数*/
const isShow = ref({
    data_show: true,
    light_district: false
});


/*横栏标签点击事件*/
const menuClick = (event)=>{

    if(event.key === "DataView"){
        console.log(event.key);
        router.push({ name: 'DataView' });
    }
    if(event.key === "MapView"){
        console.log(event.key);
        router.push({ name: 'MapView' });
    }
}

//周期函数
onMounted(() => {
    router.push({ name: 'DataView' });
});
</script>


<style scoped>

</style>
