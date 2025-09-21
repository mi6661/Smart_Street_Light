package com.gwen.smartlight.controller;

import com.gwen.smartlight.dto.web.LightInfo;
import com.gwen.smartlight.dto.web.LightStatusPage;
import com.gwen.smartlight.entity.Light;
import com.gwen.smartlight.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/light")
public class LightController {

    @Autowired
    private LightService lightService;

    @GetMapping("/all")
    public List<LightInfo> getAllLightInfos() {
        //TODO
        return lightService.getLightInfos();
    }

    /*
    * 获取路灯状态，通过地区分组
    * */
    @GetMapping("/districts")
    public List<String> getLightsStatusGroupByDistrict() {

        return lightService.getDistricts();
    }

    /*
    * 更新路灯状态
    * */
    @PostMapping("/update")
    public boolean updateLight(@RequestBody LightInfo info) {
        return lightService.updateLight(info);
    }
}
