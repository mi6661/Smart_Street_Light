// 历史数据页面逻辑
document.addEventListener('DOMContentLoaded', function() {
    // 初始化历史数据图表
    console.log("历史数据页面加载完成");
    
    // 初始化图表
    const chartContainer = document.getElementById('historyChart');
    if (chartContainer) {
        chartContainer.innerHTML = '<p style="text-align:center;padding-top:200px;">历史数据图表区域</p>';
    }
});