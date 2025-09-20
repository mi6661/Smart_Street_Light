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
                            <!--
                            <a-menu-item key="1">option1</a-menu-item>
                            <a-menu-item key="2">option2</a-menu-item>
                            <a-menu-item key="3">option3</a-menu-item>
                            <a-menu-item key="4">option4</a-menu-item>
                            -->
                            <a-menu-item v-for="district in districts" :key="district" @click.stop="ClickItem(district)">{{ district }}</a-menu-item>


                        </a-sub-menu>
                        <a-sub-menu key="sub2">
                            <template #title>
                                <span>
                                    <laptop-outlined />
                                    环境数据
                                </span>
                            </template>
                            <a-menu-item key="5">option5</a-menu-item>
                            <a-menu-item key="6">option6</a-menu-item>
                            <a-menu-item key="7">option7</a-menu-item>
                            <a-menu-item key="8">option8</a-menu-item>
                        </a-sub-menu>
                        <a-sub-menu key="sub3">
                            <template #title>
                                <span>
                                    <notification-outlined />
                                    求助反馈
                                </span>
                            </template>
                            <a-menu-item key="9">option9</a-menu-item>
                            <a-menu-item key="10">option10</a-menu-item>
                            <a-menu-item key="11">option11</a-menu-item>
                            <a-menu-item key="12">option12</a-menu-item>
                        </a-sub-menu>

                    </a-menu>
                </a-layout-sider>
                    

                <!-- 内容区域 -->
                <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
                    <div class="content-body">
                        <LightCard
                        v-for="light in lights"
                        :key="light.id"
                        :light="light"
                        @update-light="handleUpdateLight"/>
                    </div>
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
import {getDistricts, getLightList, updateLightStatus} from '../api/lightApi';
import LightCard from '../components/LightCard.vue'

const selectedKeys1 = ref<string[]>(['2']);
const selectedKeys2 = ref<string[]>(['1']);
const openKeys = ref<string[]>(['sub1']);



/**路灯状态信息列表 */
const lights = ref([]);
/**地区列表*/
const districts = ref([]);

/**获取路灯信息 */
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


/**获取地区列表*/
const fetchDistricts = async () => {
    try{
        districts.value = await getDistricts();
    }catch(error){
        console.error('获取地区列表失败:', error);
    }

}

/**上传路灯状态更新 */
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


/**边栏item点击事件处理*/
const ClickItem = (item)=>{
    console.log('item:',item);
}

//挂载时
onMounted(() => {
    fetchLights();
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
