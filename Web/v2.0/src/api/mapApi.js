import axios from "axios";

/*示例*/
const exaURL = 'https://restapi.amap.com/v3/staticmap?' +
    'location=116.481485,39.990464&' +
    'zoom=10&size=750*300&' +
    'markers=mid,,A:116.481485,39.990464' +
    '&key=<用户的key>'

const baseURL = "https://restapi.amap.com/v3/staticmap"

const KEY = "899ee66432db1860fd83d3684e9b48d9"


export const getLightMap = async (options) => {
    const key = `key=${KEY}`;
    const url = baseURL + key + options;
    try{
        const response = await axios.get(url);


        return response.data;
    }catch(err){
        console.log("路灯地图请求失败："+err)
    }
}

/*获取默认值主题的地图URL*/
export const getDefaultStyleMapURL = (locations)=>{
    let center = '123.495529,41.728208';
    let zoom = '10';
    let size = '750*300';
    let title = 'A';

    return getMapURL(center,zoom,size,title,catLocations(locations));
}


//生产标点地图链接
export const getMapURL = (center,zoom,size,title,locations)=>{

    /*示例*/
    return `${baseURL}?` +
        `location=${center}&` +
        `zoom=${zoom}&` +
        `size=${size}&` +
        `markers=mid,,${title}:${locations}` +
        `&key=${KEY}`
}

function catLocations(locations){
    let result = '';
    for(let i=0; i<locations.length; i++){
        let temp;
        if(i===locations.length-1){
            temp = `${locations[i].latitude},${locations[i].longitude}`;
        }else{
            temp = `${locations[i].latitude},${locations[i].longitude};`;
        }

        result +=`${temp}`;
    }
    return result;
}





/////暂时没有用的代码/////

/*经纬度*/
function getParamLocation(latitude,longitude){
    return `location=${latitude},${longitude}`;
}

/*地图缩放级别*/
function getParamZoom(value){
    return `zoom=${value}`;
}

/*地图大小*/
function getParamSize(width,height){
    return `size=${width}*${height}`;
}

/*设置标注
* tip：最多标注10个
* */
function getParamMarker(style,locations){
    let mark =  `${style}:`;
    for(let i=0; i<locations.length; i++){
        mark += `${locations[i].latitude},${locations[i].longitude};`;
    }
    return mark;
}

function getMarkerStyle(size,color,label){
    return `${size},${color},${label}`;
}