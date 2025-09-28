package com.gwen.smartlight.controller;


import com.gwen.smartlight.dto.web.SensorRealTimeData;
import com.gwen.smartlight.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/sensor")
public class SensorDataController {

    @Autowired
    private SensorService sensorService;

    /*获取每个路灯实时的温度，湿度，风速*/
    @GetMapping("/realtime_data")
    List<SensorRealTimeData> getAllSensorRealTimeData() {
        return sensorService.getAllSensorRealTimeData();
    }

}
