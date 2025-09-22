<template>
<div class="content-body">
    <LightCard
        v-for="light in lights"
        :key="light.id"
        :light="light"
        @update-light="handleUpdateLight"/>
</div>
</template>

<script setup>
import LightCard from "../../components/LightCard.vue";
import {onMounted, ref, watch} from "vue";
import {getLightStatusByDistrict, updateLightStatus} from "../../api/lightApi.js";

//路由出入数据
const props = defineProps({
    district: {
        required: true,
        type: String,
    }
});

watch(() => props.district, (newVal, oldVal) => {
    //console.log(newVal);
    load_data(newVal);
},{immediate: false});

/**路灯状态信息列表 */
const lights = ref([]);

/**路灯信息 */
const lightInfos = ref([]);


/**加载指定地区路灯信息*/
const load_data = async (district)=>{
    try{
        const data = await getLightStatusByDistrict(district);
        console.log(data);
        if(data.length > 0){
            lights.value = data;
        }else {
            console.log(`没有${district}的路灯数据`);
        }
    }catch(error){
        console.log("数据请求失败："+error);
    }

}


/**上传路灯状态更新 */
const handleUpdateLight = async (updatedLight) => {
    try {
        const response = await updateLightStatus(updatedLight);
        console.log(response);
        if (response) {
            const index = lights.value.findIndex(light => light.id === updatedLight.id);
            if (index !== -1) {
                lights.value[index] = { ...updatedLight, id: updatedLight.id };
            }
            console.log('路灯状态更新成功');
        } else {
            console.error('API返回更新失败:', response);
        }
    } catch (error) {
        console.error('更新路灯状态失败:', error);
    }
};

onMounted(() => {
    //console.log("props.district", props.district);
    load_data(props.district);
})

</script>

<style scoped>

</style>