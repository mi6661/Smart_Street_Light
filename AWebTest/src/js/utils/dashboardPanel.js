// dashboardPanel.js

function createDashboardPanel(containerId, data) {
    const container = document.getElementById(containerId);
  
    const {
      lampControl = {},
      environment = {}
    } = data;
  
    const lampPanel = document.createElement('div');
    lampPanel.className = 'control-panel';
    lampPanel.innerHTML = `
      <div class="panel-header">
        <div class="panel-icon">💡</div>
        <h3>路灯控制</h3>
        <span class="status-badge status-online">${lampControl.status==='on'?'在线':'离线' || '离线'}</span>
      </div>
  
      <div class="lamp-visual">
        <img class="lamp-image" src="${ lampControl.status==='on' ? lampImages.on:lampImages.off || ''}" alt="智慧路灯">
      </div>
  
      <table class="status-table">
        <tr><td>设备编号</td><td class="device-id">${lampControl.deviceId || 'null'}</td></tr>
        <tr><td>当前状态</td><td class="status-text">${lampControl.statusText || 'null'}</td></tr>
        <tr><td>工作电流</td><td class="current-value">${lampControl.currentValue || 'null'}</td></tr>
        <tr><td>实时功率</td><td class="power-value">${lampControl.powerValue || 'null'}</td></tr>
        <tr><td>亮度等级</td><td class="brightness-value">${lampControl.brightnessValue || 'null'}</td></tr>
      </table>
  
      <div class="brightness-control">
        <div class="brightness-header">
          <span>亮度调节</span>
          <span class="brightness-percent">${lampControl.brightnessValue || '0%'}</span>
        </div>
        <input type="range" class="brightness-slider" min="0" max="100" value="${lampControl.brightnessValue || 0}">
      </div>
  
      <div class="control-group">
        <button class="control-btn btn-on">
          <span class="btn-icon">🔆</span>开启路灯
        </button>
        <button class="control-btn btn-off">
          <span class="btn-icon">🌙</span>关闭路灯
        </button>
      </div>
    `;
  
    const envPanel = document.createElement('div');
    envPanel.className = 'control-panel';
    envPanel.innerHTML = `
      <div class="panel-header">
        <div class="panel-icon">🌡️</div>
        <h3>环境监测</h3>
      </div>
  
      <div class="sensor-card">
        <h4>温度</h4>
        <div class="sensor-value">${environment.tempValue || '--'}℃</div>
        <div>更新时间: <span class="temp-time">${environment.time || '--'}</span></div>
      </div>
  
      <div class="sensor-card">
        <h4>湿度</h4>
        <div class="sensor-value">${environment.humiValue || '--'}%</div>
        <div>更新时间: <span class="humi-time">${environment.tTime || '--'}</span></div>
      </div>
  
      <div class="sensor-card">
        <h4>光照强度</h4>
        <div class="sensor-value">${environment.luxValue || '--'}LUX</div>
        <div>更新时间: <span class="lux-time">${environment.time || '--'}</span></div>
      </div>
    `;
  
    // 添加逻辑交互
    const slider = lampPanel.querySelector('.brightness-slider');
    const brightnessPercent = lampPanel.querySelector('.brightness-percent');
    const lampImage = lampPanel.querySelector('.lamp-image');
  
    //亮度调节监听
    slider.addEventListener('input', () => {
      const percent = slider.value + '%';
      brightnessPercent.textContent = percent;
      lampPanel.querySelector('.brightness-value').textContent = percent;
    });
    
    //开启按钮监听
    lampPanel.querySelector('.btn-on').addEventListener('click', (e) => {
      const panel = e.currentTarget.closest('.control-panel');
      panel.querySelector('.status-text').textContent = '已开启';
      lampImage.src = ""
    });
    
    //关闭按钮监听
    lampPanel.querySelector('.btn-off').addEventListener('click', (e) => {
      const panel = e.currentTarget.closest('.control-panel');
      panel.querySelector('.status-text').textContent = '已关闭';
    });
  
    
    container.appendChild(lampPanel);
    container.appendChild(envPanel);
  }