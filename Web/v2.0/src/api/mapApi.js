import axios from "axios";

/*腾讯地图key*/
export const T_KEY = 'LOVBZ-TRDK7-UGMXW-HKZSQ-HSSZ2-5PBEQ'

/*地图路灯图标*/
export const ICON_LIGHT = 'src/asset/icon/light.png'
export const ICON_LIGHT_2 = 'src/asset/icon/light2.png'

/*获取路灯经纬度*/
export const LIGHT_SITES = async ()=>{
    try{
        const response = await axios.get("/light/light_sites");
        return response.data;
    }catch(e){
        console.error("获取路灯经纬度失败："+e)
    }
}