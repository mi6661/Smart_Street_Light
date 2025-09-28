/*
* 获取所有路灯的最新温湿度和风速
* */

import axios from "axios";

export const getAllSensorsRealTimeData = async () =>{
    try{
        const response = await axios.get('sensor/realtime_data');
        return response.data;
    }catch(error){
        console.log("获取所有路灯的最新温湿度和风速失败："+error);
    }
}