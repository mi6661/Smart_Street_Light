// 设备管理页面逻辑
document.addEventListener('DOMContentLoaded', function() {
    // 初始化设备列表
    const deviceList = document.getElementById('deviceList');
    if (deviceList) {
        const devices = [
            { id: 'LD-0237-5A', status: '在线', location: '南屏中路中段' },
            { id: 'LD-0238-5B', status: '在线', location: '南屏中路东段' },
            { id: 'LD-0239-5C', status: '离线', location: '南屏中路西段' }
        ];
        
        let html = '';
        devices.forEach(device => {
            html += `
                <div style="padding: 1rem; border-bottom: 1px solid rgba(255,255,255,0.1);">
                    <div><strong>${device.id}</strong></div>
                    <div>位置: ${device.location}</div>
                    <div>状态: <span style="color: ${device.status === '在线' ? '#00ff88' : '#ff4757'}">${device.status}</span></div>
                </div>
            `;
        });
        
        deviceList.innerHTML = html;
    }
});