// 设备状态数据
let devices = [];
let device = {
    id: null,
    location: null,
    status: null,
    brightness: null,
    auto: null,
    current: null,
    power: null,
    temp: null,
    humi: null,
    pm25: null,
    lux: null,
    create_time: null,
    lastUpdate: new Date(),
}

let datas = [];

let data = {
    lampControl : {
        status: 'off',
        iampImageUrl : '../src/res/off.jpg',
        statusText: '已开启',
        currentValue: 12,
        powerValue: 34,
        brightnessValue: 56
    },
    environment: {
        tempValue: 12,
        humiValue: 34,
        luxValue:56,
        time: '2025-04-04'
    }
}

function update(device) {
    let url = "http://127.0.0.1:8081/light/update";
    let lightInfo = {
        id: device.id,
        location: device.location,
        status: device.status,
        brightness: device.brightness,
        auto: device.auto,
    }
    fetch(url,{
        method: 'POST',
        mode: 'cors',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(lightInfo),
    }).then(response => {
        console.log(response.json());
    })
}




//路灯图片资源
const lampImages = {
    on: '../res/on.jpg',
    off:'../res/off.jpg'
};

function load(){
    fetch("http://127.0.0.1:8081/light/real_time_list").then(response=>{
        if (!response.ok) {
            console.error("fetch error");
        }
        return response.json();
    }).then(response=>{
        //console.log(response);
        for (let i = 0; i < response.length; i++) {
            let light = response[i]["light"];
            let sensorData = response[i]["sensorData"];
            devices[i] = {
                lampControl : {
                    deviceId: light["_id"],
                    status: light['status'],
                    iampImageUrl : '../src/res/off.jpg',
                    statusText: light['status']=="on"?"已开启":"已关闭",
                    currentValue: null,
                    powerValue: null,
                    brightnessValue: light['brightness']
                },
                environment: {
                    tempValue: sensorData!=null ? sensorData['temperature']:null,
                    humiValue: sensorData!=null ? sensorData['humidity']:null,
                    luxValue:null,
                    time: sensorData!=null ? sensorData['create_time']:null
                }
            };
        }
    }).then(()=>{
        devices.forEach(d=>{
            createDashboardPanel('main',d);
        })

        
    })
}







// 初始化设备状态
document.addEventListener('DOMContentLoaded', function() {
    load();
});

//定时器
setInterval(() => {
    //load();
}, 3000);