import axios from 'axios';

// 使用相对路径，Vite代理会自动处理
const API_BASE_URL = '';

// 获取所有路灯状态
export const getLightList = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/light/all`);
        return response.data;
    } catch (error) {
        console.error('获取路灯列表失败:', error);
        throw error;
    }
};

// 更新路灯状态
export const updateLightStatus = async (light) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/light/update`, light);
        return response.data;
    } catch (error) {
        console.error('更新路灯状态失败:', error);
        throw error;
    }
};

// 通过路灯ID获取传感器数据
export const getSensorDataById = async (id) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/sensor/id?id=${id}`);
        return response.data;
    } catch (error) {
        console.error(`获取路灯 ${id} 的传感器数据失败:`, error);
        throw error;
    }
};


//获取路灯所在的地区
export const getDistricts = async () => {
    try{
        const response = await axios.get(`${API_BASE_URL}/light/districts`);
        return response.data;
    }catch(error){
        console.error('获取地区列表失败:', error);
    }
}