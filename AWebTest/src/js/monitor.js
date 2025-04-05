// 实时监控页面逻辑
document.addEventListener('DOMContentLoaded', function() {
    //初始化实时监控图表
    console.log("实时监控页面加载完成");
    
    // 初始化图表
    const chartContainer = document.getElementById('monitorChart');
    if (chartContainer) {
        chartContainer.innerHTML = '<p style="text-align:center;padding-top:200px;">实时监控图表区域</p>';
    }
});