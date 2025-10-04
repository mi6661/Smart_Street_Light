/*
* 获取所有路灯的最新温湿度和风速
* */

import axios from "axios";


/*获取路灯实时数据*/
export const getAllSensorsRealTimeData = async () =>{
    try{
        const response = await axios.get('sensor/realtime_data');
        return response.data;
    }catch(error){
        console.log("获取所有路灯的最新温湿度和风速失败："+error);
    }
}

/*获取传感器统计数据*/
export const getSensorsAnalysisData = async () =>{
    try{
        const response = await axios.get('sensor/avgdata');
        return response.data;
    }catch(error){
        console.log("获取传感器统计数据失败"+error);
    }
}