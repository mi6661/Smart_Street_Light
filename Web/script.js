// 路灯数据测试数据
let lights = [
    { id: 1, location: '主街道', status: 'on', brightness: 80 , auto: 'on'},
    { id: 2, location: '公园大道', status: 'off', brightness: 0 , auto: 'off'},
    { id: 3, location: '商业区', status: 'on', brightness: 60 , auto: 'off'},
    { id: 4, location: '住宅区', status: 'off', brightness: 40 , auto: 'off'}
];

//路灯实体类
class Light{
    constructor(id,location,status,brightness,auto){
        this.id = id;
        this.location = location;
        this.status = status;
        this.brightness = brightness;
        this.auto = auto;
    }
    getLight(){
        let light = {
            id: this.id,
            location: this.location,
            status: this.status,
            brightness: this.brightness,
            auto: this.auto
        }
        return light;
    }
};


//发送get请求
async function getData(url) {
    try {
        let response = await fetch(url);
        if (!response.ok) {
            throw new Error("Network response was not ok: " + response.statusText);
        }
        let data = await response.json(); // 解析 JSON
        for(var i=0;i<data.length;i++){
            let id = data[i]._id;
            let location = data[i].location;
            let status = data[i].status;
            let brightness = data[i].brightness;
            let auto = data[i].auto;
            light = new Light(id,location,status,brightness,auto);
            lights.push(light);
        }
        //数据请求完成后，初始化界面
        console.log(data);
        Init();
    } catch (error) {
        console.log('There was a problem with your fetch operation:', error);
    }
    
}

//发送post请求
export async function SendData(url,data){

    fetch(url,{
        method: 'POST',
        head:{
            'Content-Type':'application/json',//这里根据数据类型调整
        },
        body: JSON.stringify(data),//数据类型要与headers匹配
    })
    .then(response=>{
        console.log(response.json());
    })
    .catch(error=>{
        console.error('Error:',error);
    })
}




//初始化页面
function Init() {
    const lightGrid = document.getElementById('light-grid');
    const lightControl = document.getElementById('light-control');
    const controlTitle = document.getElementById('control-title');
    const toggleBtn = document.getElementById('toggle-btn');
    const autoBtn = document.getElementById('auto-btn');
    const brightnessSlider = document.getElementById('brightness-slider');
    const brightnessValue = document.getElementById('brightness-value');

    let selectedLight = null;

    // 渲染路灯卡片
    function renderLights() {
        lightGrid.innerHTML = '';
        lights.forEach(light => {
            const card = document.createElement('div');

            //判断是否是自动状态
            if(light.auto=='on'){
                card.className = `light-card auto`;
            }else{
                card.className = `light-card ${light.status}`;
            }


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
        autoBtn.textContent = light.auto === 'on' ? '关闭自动调节' : '开启自动调节';
        brightnessSlider.value = light.brightness;
        brightnessValue.textContent = `${light.brightness}%`;
        lightControl.style.display = 'block';
    }

    // 切换路灯开关状态
    toggleBtn.addEventListener('click', function() {
        
        if (selectedLight) {
            selectedLight.status = selectedLight.status === 'on' ? 'off' : 'on';
            toggleBtn.textContent = selectedLight.status === 'on' ? '关闭路灯' : '开启路灯';
            renderLights();

            data = new Light(
                selectedLight.id,
                selectedLight.location,
                selectedLight.status,
                selectedLight.brightness,
                selectedLight.auto 
            )
            console.log(data);
        }
    });

    //切换路灯自动状态
    autoBtn.addEventListener('click',function(){
        if(selectedLight){
            selectedLight.auto = selectedLight.auto === 'on' ? 'off' : 'on';
            autoBtn.textContent = selectedLight.auto === 'on' ? '关闭自动调节' : '开启自动调节';
            renderLights();//重新渲染


            data = new Light(
                selectedLight.id,
                selectedLight.location,
                selectedLight.status,
                selectedLight.brightness,
                selectedLight.auto 
            )
            console.log(data);
        }
    })


    // 调节亮度
    brightnessSlider.addEventListener('input', function() {
        if (selectedLight) {
            selectedLight.brightness = this.value;
            brightnessValue.textContent = `${this.value}%`;
            renderLights();

            data = new Light(
                selectedLight.id,
                selectedLight.location,
                selectedLight.status,
                selectedLight.brightness,
                selectedLight.auto
            )
            console.log(data);
        }
    });

    // 初始渲染
    renderLights();
}




getData("http://127.0.0.1:8081/light/list");//从后端更新lights中的数据
console.log(Test());