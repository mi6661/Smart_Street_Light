package org.example.application.controller;


import org.example.application.dao.*;
import org.example.application.entity.StreetLight;
import org.example.application.response.ApiResonse;
import org.example.application.service.LightService;
import org.example.application.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/light")
public class LightRequest {

    @Autowired
    private LightService lightService;
    @Autowired
    private SensorService sensorService;

    //路灯授时api
    @GetMapping("/time")
    public String getTime(){
        Date date = new Date();
        long timestamp = System.currentTimeMillis();
        return String.valueOf(timestamp);
    }

    //所有路灯信息
    @GetMapping("/list")
    public List<StreetLight> test(){
        return lightService.getAllLights();
    }

    //通过id查寻路灯状态
    @PostMapping("/id")
    public ApiResonse<LightInfo> findById(@RequestParam int id){
        LightInfo lightInfo = lightService.getLightInfo(id);
        if(lightInfo == null){
            return ApiResonse.fail("该路灯不存在");
        }
        return ApiResonse.success(lightInfo);
    }

    //添加路灯
    @PostMapping("/addLight")
    public ApiResonse<Boolean> addLight(@RequestBody LightInfo light){
        if (lightService.addLight(light)){
            return ApiResonse.success(true);
        }
        return ApiResonse.fail("添加失败");
    }

    //修改状态
    @PostMapping("/update")
    public ApiResonse<Boolean> updateLight(@RequestBody LightInfo light){
        System.out.println(light.toString());
        if(lightService.update(light)){
            return ApiResonse.success(true);
        }
        return ApiResonse.fail("更新失败");
    }
    //硬件传入温度湿度等数据
    @PostMapping("/updates")
    public ApiResonse<Boolean> updateLights(@RequestBody LightSensor info){
        //路灯控制数据信息
        LightInfo light = new LightInfo();
        light.id = info.id;
        light.location = info.location;
        light.status = info.status;
        light.brightness = info.brightness;
        light.auto = info.auto;
        //传感器数据信息
        SensorDao sensor = new SensorDao();
        sensor.light_id = info.id;
        sensor.temperature = info.temperature;
        sensor.humidity = info.humidity;
        sensor.pm24 = -1;//目前硬件还没有pm2.5检测
        if(lightService.update(light)&&sensorService.insertData(sensor)){
            return  ApiResonse.success(true);
        }
        return ApiResonse.fail("添加失败");
    }

    //返回路灯和传感器匹配的数据集合
    @GetMapping("/listinfo")
    public List<LightWithAllSensorData> getLightsandsensors(){
        return lightService.getAllLightsWithSensorData();
    }

    //返回路灯信息和路灯实时数据
    @GetMapping("/real_time_list")
    public List<LightWithLastSensorData> getRealtimeLights(){
        return lightService.getAllLightsWithLastSensorData();
    }
}
