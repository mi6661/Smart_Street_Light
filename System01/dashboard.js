// DOM元素
const elements = {
    lampImage: document.getElementById('lampImage'),
    statusBadge: document.getElementById('statusBadge'),
    statusText: document.getElementById('statusText'),
    deviceId: document.getElementById('deviceId'),//设备id
    currentValue: document.getElementById('currentValue'),
    powerValue: document.getElementById('powerValue'),
    brightnessValue: document.getElementById('brightnessValue'),
    brightnessPercent: document.getElementById('brightnessPercent'),
    brightnessSlider: document.getElementById('brightnessSlider'),
    btnOn: document.getElementById('btnOn'),
    btnOff: document.getElementById('btnOff'),
    tempValue: document.getElementById('tempValue'),
    humiValue: document.getElementById('humiValue'),
    luxValue: document.getElementById('luxValue'),
    tempTime: document.getElementById('tempTime'),
    humiTime: document.getElementById('humiTime'),
    luxTime: document.getElementById('luxTime')
};
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
    lastUpdate: new Date()
}
// 更新设备状态
function updateDeviceStatus() {
    // 更新路灯图片
    elements.lampImage.src = device.status === "on" ? lampImages.on : lampImages.off;

    // 更新状态显示
    if (device.status === "on") {
        elements.statusBadge.textContent = '在线';
        elements.statusBadge.className = 'status-badge status-online';
        elements.statusText.textContent = '运行中';
        elements.statusText.style.color = '#00ff88';
    } else {
        elements.statusBadge.textContent = '离线';
        elements.statusBadge.className = 'status-badge status-offline';
        elements.statusText.textContent = '已关闭';
        elements.statusText.style.color = '#ff4757';
    }

    // 更新数值
    elements.deviceId.textContent = device.id;
    elements.currentValue.textContent = `${device.current}`;
    elements.powerValue.textContent = `${device.power}W`;
    elements.brightnessValue.textContent = `${device.brightness}%`;
    elements.brightnessPercent.textContent = `${device.brightness}%`;
    elements.brightnessSlider.value = device.brightness;

    // 更新环境数据
    elements.tempValue.textContent = `${device.temp}℃`;
    elements.humiValue.textContent = `${device.humi}%`;
    elements.luxValue.textContent = `${device.lux}LUX`;

    // 更新时间显示
    updateTimeDisplay();
}

function updateData(device) {
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


// 更新时间显示
function updateTimeDisplay() {
    const now = new Date();
    const diffInSeconds = Math.floor((now - device.lastUpdate) / 1000);

    if (diffInSeconds < 60) {
        elements.tempTime.textContent = '刚刚';
        elements.humiTime.textContent = '刚刚';
        elements.luxTime.textContent = '刚刚';
    } else if (diffInSeconds < 3600) {
        const minutes = Math.floor(diffInSeconds / 60);
        elements.tempTime.textContent = `${minutes}分钟前`;
        elements.humiTime.textContent = `${minutes}分钟前`;
        elements.luxTime.textContent = `${minutes}分钟前`;
    } else {
        const hours = Math.floor(diffInSeconds / 3600);
        elements.tempTime.textContent = `${hours}小时前`;
        elements.humiTime.textContent = `${hours}小时前`;
        elements.luxTime.textContent = `${hours}小时前`;
    }
}


//路灯图片资源
const lampImages = {
    on: './res/on.jpg',
    off:'./res/off.jpg'
};

function fresh(){
    fetch("http://127.0.0.1:8081/light/real_time_list").then(response=>{
        if (!response.ok) {
            console.error("fetch error");
        }
        return response.json();
    }).then(response=>{
        for (let i = 0; i < response.length; i++) {
            let light = response[i]["light"];
            let sensorData = response[i]["sensorData"];
            devices[i] = {
                id: light["_id"],
                location: light["location"],
                status: light["status"],
                brightness: light["brightness"],
                auto: light["auto"],
                current: null,
                power: null,
                temp: sensorData != null ? sensorData["temperature"] : null,
                humi: sensorData != null ? sensorData["humidity"] : null,
                pm25: sensorData != null ? sensorData["pm24"] : null,
                lux: null,
                lastUpdate: new Date()
            };
        }
    }).then(()=>{
        device = devices[0];
        console.log(device);
        updateDeviceStatus();
    })
}






// 开启路灯
elements.btnOn.addEventListener('click', () => {
    console.log("click on");
    device.status = "on";
    device.lastUpdate = new Date();
    updateData(device);
    updateDeviceStatus();
});

// 关闭路灯
elements.btnOff.addEventListener('click', () => {
    console.log("click off");
    device.status = "off";
    device.lastUpdate = new Date();
    updateData(device);
    updateDeviceStatus();
});

// 亮度调节
elements.brightnessSlider.addEventListener('input', (e) => {
    if (!device.status) return;
    device.brightness = parseInt(e.target.value);
    device.current = null;
    device.power = null;
    device.lastUpdate = new Date();
    updateData(device);
    updateDeviceStatus();
});



// 初始化设备状态
document.addEventListener('DOMContentLoaded', function() {
    fresh();
    updateDeviceStatus();
});
setInterval(() => {
    fresh();
    updateDeviceStatus();
}, 3000);