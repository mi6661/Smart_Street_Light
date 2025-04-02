// 设备状态数据
const device = {
    id: 'LD-0237-5A',
    status: true,
    brightness: 85,
    current: 2.3,
    power: 150,
    temp: 25.3,
    humi: 62,
    lux: 2300,
    lastUpdate: new Date()
};

// DOM元素
const elements = {
    lampImage: document.getElementById('lampImage'),
    statusBadge: document.getElementById('statusBadge'),
    statusText: document.getElementById('statusText'),
    deviceId: document.getElementById('deviceId'),
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

//路灯图片资源
const lampImages = {
    on: 'on.jpg',
    off: 'off.jpg'
};

// 更新设备状态
function updateDeviceStatus() {
    // 更新路灯图片
    elements.lampImage.src = device.status ? lampImages.on : lampImages.off;

    // 更新状态显示
    if (device.status) {
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
    elements.currentValue.textContent = `${device.current.toFixed(1)}A`;
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

// 开启路灯
elements.btnOn.addEventListener('click', () => {
    device.status = true;
    device.brightness = 100;
    device.current = 2.3;
    device.power = 150;
    device.lastUpdate = new Date();
    updateDeviceStatus();

    // 模拟环境数据
    simulateEnvironmentData();
});

// 关闭路灯
elements.btnOff.addEventListener('click', () => {
    device.status = false;
    device.brightness = 0;
    device.current = 0.0;
    device.power = 0;
    device.lux = 0;
    device.lastUpdate = new Date();
    updateDeviceStatus();
});

// 亮度调节
elements.brightnessSlider.addEventListener('input', (e) => {
    if (!device.status) return;

    device.brightness = parseInt(e.target.value);
    device.current = (device.brightness / 100 * 2.3).toFixed(1);
    device.power = Math.round(device.brightness / 100 * 150);
    device.lastUpdate = new Date();
    updateDeviceStatus();
});

// 模拟环境数据变化
function simulateEnvironmentData() {
    if (!device.status) return;

    // 随机微小变化
    device.temp = (25 + Math.random() * 2).toFixed(1);
    device.humi = Math.round(60 + Math.random() * 10);
    device.lux = Math.round(2000 + Math.random() * 600);
    device.lastUpdate = new Date();

    updateDeviceStatus();

    // 每5秒更新一次环境数据
    setTimeout(simulateEnvironmentData, 5000);
}

// 初始化设备状态
document.addEventListener('DOMContentLoaded', function() {
    updateDeviceStatus();
    simulateEnvironmentData();
});
    