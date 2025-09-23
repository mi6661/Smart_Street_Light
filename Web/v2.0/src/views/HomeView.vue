<template>
    <a-layout>
        <!-- 顶部标签栏 -->
        <a-layout-header class="header">
            <div class="logo" />
            <a-menu v-model:selectedKeys="selectedKeys1" theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
                <a-menu-item key="1">数据查看</a-menu-item>
                <a-menu-item key="2">路灯分布</a-menu-item>
            </a-menu>
        </a-layout-header>
        <a-layout-content style="padding: 0 50px">
            <!-- 路径显示栏 -->
            <a-breadcrumb style="margin: 16px 0">
                <a-breadcrumb-item>Home</a-breadcrumb-item>
                <a-breadcrumb-item>List</a-breadcrumb-item>
                <a-breadcrumb-item>App</a-breadcrumb-item>
            </a-breadcrumb>
            <a-layout style="padding: 24px 0; background: #fff">
                <!-- 侧边栏 -->
                <a-layout-sider width="200" style="background: #fff">
                    <a-menu v-model:selectedKeys="selectedKeys2" v-model:openKeys="openKeys" mode="inline"
                            style="height: 100%">
                        <!-- 侧边栏标签 -->
                        <a-sub-menu key="sub1">
                            <template #title>
                                <span>
                                    <user-outlined />
                                    路灯状态
                                </span>
                            </template>
                            <a-menu-item v-for="district in districts"
                                         :key="district"
                                         @click.stop="ClickLightStatus(district)">{{ district }}</a-menu-item>
                        </a-sub-menu>
                        <a-sub-menu key="sub2">
                            <template #title>
                                <span>
                                    <laptop-outlined />
                                    环境数据
                                </span>
                            </template>
                            <a-menu-item key="realtime" @click.stop="ClickEnviDataTemp('realtime')">实时监测</a-menu-item>
                            <a-menu-item key="statistics" @click.stop="ClickEnviDataTemp('statistics')">数据统计</a-menu-item>
                        </a-sub-menu>
                        <a-sub-menu key="sub3">
                            <template #title>
                                <span>
                                    <notification-outlined />
                                    求助反馈
                                </span>
                            </template>
                            <a-menu-item key="9">功能完善中</a-menu-item>
                        </a-sub-menu>

                    </a-menu>
                </a-layout-sider>


                <!-- 内容区域 -->
                <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
                    <!--路由窗口-->
                    <router-view></router-view>
                </a-layout-content>

            </a-layout>
        </a-layout-content>
        <a-layout-footer style="text-align: center">
            Ant Design ©2018 Created by Ant UED
        </a-layout-footer>
    </a-layout>
</template>


<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import {LaptopOutlined, NotificationOutlined, UserOutlined} from '@ant-design/icons-vue';
import {getDistricts, getLightList, updateLightStatus,getLightStatusByDistrict} from '../api/lightApi';
import {useRouter} from 'vue-router';

const selectedKeys1 = ref<string[]>(['2']);
const selectedKeys2 = ref<string[]>(['1']);
const openKeys = ref<string[]>(['sub1']);
const router = useRouter();


/**地区列表*/
const districts = ref([]);


/**获取地区列表*/
const fetchDistricts = async () => {
    try{
        districts.value = await getDistricts();
    }catch(error){
        console.error('获取地区列表失败:', error);
    }

}





/**边栏"路灯状态"点击事件处理*/
const ClickLightStatus = (item)=>{
    console.log("跳转到："+item);
    router.push({name: 'LightsStatus', params: {district: item}});
}

const ClickEnviDataTemp = (choices)=>{
    if(choices == "realtime"){
        console.log("realtime")
        router.push({name: 'RealTimeView', params: {choice: choices}});
    }
    if(choices == "statistics"){
        console.log("statistics")
        router.push({name: 'StatisticsView', params: {choice: choices}});
    }
}

//挂载时
onMounted(() => {
    fetchDistricts();
});
</script>





<style scoped>
.content-body{
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}
</style>
