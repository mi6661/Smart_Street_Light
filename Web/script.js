// 路灯数据
const lights = [
    { id: 1, location: '主街道', status: 'on', brightness: 80 },
    { id: 2, location: '公园大道', status: 'off', brightness: 0 },
    { id: 3, location: '商业区', status: 'on', brightness: 60 },
    { id: 4, location: '住宅区', status: 'on', brightness: 40 }
];

// 初始化页面
document.addEventListener('DOMContentLoaded', function() {
    const lightGrid = document.getElementById('light-grid');
    const lightControl = document.getElementById('light-control');
    const controlTitle = document.getElementById('control-title');
    const toggleBtn = document.getElementById('toggle-btn');
    const brightnessSlider = document.getElementById('brightness-slider');
    const brightnessValue = document.getElementById('brightness-value');
    
    let selectedLight = null;

    // 渲染路灯卡片
    function renderLights() {
        lightGrid.innerHTML = '';
        lights.forEach(light => {
            const card = document.createElement('div');
            card.className = `light-card ${light.status}`;
            card.innerHTML = `
                <h3>${light.location}</h3>
                <p>状态: ${light.status === 'on' ? '开启' : '关闭'}</p>
                <p>亮度: ${light.brightness}%</p>
            `;
            card.addEventListener('click', () => selectLight(light));
            lightGrid.appendChild(card);
        });
    }

    // 选择路灯
    function selectLight(light) {
        selectedLight = light;
        controlTitle.textContent = `控制面板 - ${light.location}`;
        toggleBtn.textContent = light.status === 'on' ? '关闭路灯' : '开启路灯';
        brightnessSlider.value = light.brightness;
        brightnessValue.textContent = `${light.brightness}%`;
        lightControl.style.display = 'block';
    }

    // 切换路灯状态
    toggleBtn.addEventListener('click', function() {
        if (selectedLight) {
            selectedLight.status = selectedLight.status === 'on' ? 'off' : 'on';
            toggleBtn.textContent = selectedLight.status === 'on' ? '关闭路灯' : '开启路灯';
            renderLights();
        }
    });

    // 调节亮度
    brightnessSlider.addEventListener('input', function() {
        if (selectedLight) {
            selectedLight.brightness = this.value;
            brightnessValue.textContent = `${this.value}%`;
            renderLights();
        }
    });

    // 初始渲染
    renderLights();
});