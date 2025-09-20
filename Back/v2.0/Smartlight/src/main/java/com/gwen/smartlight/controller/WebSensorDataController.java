package com.gwen.smartlight.controller;

import com.gwen.smartlight.dto.web.SensorDataOnDetailCard;
import com.gwen.smartlight.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensor")
public class WebSensorDataController {
    @Autowired
    private SensorService sensorService;

    /*通过路灯id，返回最近50条传感器数据记录*/
    @GetMapping("/list")
    List<SensorDataOnDetailCard> getSensorData(@RequestParam int id,@RequestParam int amount) {
        if(id<=0 || amount <=0) return null;
        if(amount < 1000){
            return sensorService.getSensorDataListOnDetailCardsById(id,amount);
        }else{
            return sensorService.getSensorDataListOnDetailCardsById(id,100);
        }
    }

}
