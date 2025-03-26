package org.example.application.controller;

import org.example.application.dao.SensorDao;
import org.example.application.entity.SensorData;
import org.example.application.repository.SensorRepository;
import org.example.application.response.ApiResonse;
import org.example.application.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorRequest {

    @Autowired
    private SensorService sensorService;


    //##这些是硬件调用的api##
    //添加路灯灯传感器数据
    @PostMapping("/add")
    public boolean insertData(@RequestParam int light_id,@RequestParam float temperature,@RequestParam float humidity,float pm24){
        return sensorService.insertData(new SensorDao(light_id,temperature,humidity,pm24));
    }


    //##这些是前端需要的api##
    //1.查询所有路灯的所有数据
    @GetMapping("/all")
    public ApiResonse<List<SensorData>> getAllSensors() {
        List<SensorData> allData = sensorService.getAllData();
        if (allData != null) {
            return ApiResonse.success(allData);
        }
        return ApiResonse.fail("数据为空");
    }
    //2.查询当路灯的所有数据
    @PostMapping("/id")
    public ApiResonse<List<SensorData>> getSensorById(@RequestParam int id) {
        List<SensorData> allDataByLightId = sensorService.getAllDataByLightId(id);
        if (allDataByLightId != null) {
            return ApiResonse.success(allDataByLightId);
        }
        return ApiResonse.fail("查询失败");
    }

}
