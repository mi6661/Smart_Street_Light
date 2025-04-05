// 导航栏交互
function initNavigation() {
    const navItems = document.querySelectorAll('.nav-item');
    const navIndicator = document.querySelector('.nav-indicator');
    const currentPage = window.location.pathname.split('/').pop() || 'index.html';

    // 设置当前活动页面
    navItems.forEach(item => {
        const link = item.querySelector('a');
        if (link && link.getAttribute('href') === currentPage) {
            item.classList.add('active');
        } else {
            item.classList.remove('active');
        }
    });

    // 更新指示器位置
    function updateIndicator() {
        const activeItem = document.querySelector('.nav-item.active');
        if (activeItem) {
            const itemRect = activeItem.getBoundingClientRect();
            const containerRect = activeItem.parentElement.getBoundingClientRect();
            
            navIndicator.style.width = `${itemRect.width}px`;
            navIndicator.style.left = `${itemRect.left - containerRect.left}px`;
        }
    }

    // 初始化指示器位置
    updateIndicator();

    // 窗口大小改变时重新定位
    window.addEventListener('resize', updateIndicator);
}

// 页面加载时初始化
document.addEventListener('DOMContentLoaded', function() {
    initNavigation();
});