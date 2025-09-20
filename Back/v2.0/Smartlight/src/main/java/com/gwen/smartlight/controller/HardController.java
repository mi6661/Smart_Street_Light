package com.gwen.smartlight.controller;
/*
* 开发板访问接口
* */

import com.gwen.smartlight.dto.LightStatus;
import com.gwen.smartlight.dto.SensorInfo;
import com.gwen.smartlight.mapper.SensorMapper;
import com.gwen.smartlight.service.LightService;
import com.gwen.smartlight.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hard")
public class HardController {

    @Autowired
    private LightService lightService;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private SensorMapper sensorMapper;


    /*
    * 硬件端上传传感器数据
    * */
    @PostMapping("/update")
    public int update(@RequestBody SensorInfo sensorInfo){
        try{
            //System.out.println(sensorInfo.toString());
            return sensorService.HardDataUpdate(sensorInfo)?1:0;
        }catch (Exception e){
            System.err.println("硬件端上传数据失败！");
            System.err.println(e.getMessage());
            return 0;
        }
    }


    /*硬件端同步路灯状态*/
    @GetMapping("/load")
    public LightStatus load(@RequestParam int id){
        return lightService.getLightStatusById(id);
    }

}
