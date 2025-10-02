<!--
二级路由页面："数据查看"
辖下子页面：路灯状态、环境数据、求助反馈
-->



<template>
    <!--数据查看页面-->
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

</template>

<script setup lang="ts">
import {onMounted,ref} from "vue";
import {LaptopOutlined, NotificationOutlined, UserOutlined} from "@ant-design/icons-vue";
import {useRouter} from "vue-router";
import {getDistricts} from "../../api/lightApi"
const selectedKeys2 = ref<string[]>(['1']);
const openKeys = ref<string[]>(['sub1']);

/**地区列表*/
const districts = ref([]);

const router = useRouter();



/**获取地区列表*/
const fetchDistricts = async () => {
    try{
        districts.value = await getDistricts();
    }catch(error){
        console.log('获取地区列表失败:', error);
    }
}

/**边栏"路灯状态"点击事件处理*/
const ClickLightStatus = (item)=>{
    router.push({name: 'LightsStatus', params: {district: item}});
}
/*环境数据边栏*/
const ClickEnviDataTemp = (choices)=>{
    if(choices === "realtime"){
        router.push({name: 'RealTimeView'});
    }
    if(choices === "statistics"){
        router.push({name: 'StatisticsView'});
    }
}


//周期函数
onMounted(() => {
    fetchDistricts();
});

</script>

<style scoped>

</style>