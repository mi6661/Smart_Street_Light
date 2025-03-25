package org.example.application.controller;


import org.example.application.dao.LightInfo;
import org.example.application.entity.StreetLight;
import org.example.application.repository.LightRepository;
import org.example.application.response.ApiResonse;
import org.example.application.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/light")
public class LightRequest {

    @Autowired
    private LightService lightService;

    @GetMapping("/test")
    public List<StreetLight> test(){
        return lightService.getAllLights();
    }


    @PostMapping("/id")
    public ApiResonse<LightInfo> findById(@RequestParam int id){
        LightInfo lightInfo = lightService.getLightInfo(id);
        if(lightInfo == null){
            return ApiResonse.fail("该路灯不存在");
        }
        return ApiResonse.success(lightInfo);
    }

    @PostMapping("/addLight")
    public ApiResonse<Boolean> addLight(@RequestBody LightInfo light){
        if (lightService.addLight(light)){
            return ApiResonse.success(true);
        }
        return ApiResonse.fail("添加失败");
    }

    //修改单个路灯状态
    @PostMapping("/changeStatus")
    public ApiResonse<Boolean> updateLightStatus(@RequestParam int id, @RequestParam int status){
        if (lightService.updateStatus(id,status)) return ApiResonse.success(true);
        return ApiResonse.fail("状态修改失败");
    }

    //修改单个路灯亮度
    @PostMapping("/changeBrightness")
    public ApiResonse<Boolean> updateLightBrightness(@RequestParam int id,@RequestParam int brightness){
        if (lightService.updateBrightness(id,brightness)) return ApiResonse.success(true);
        return ApiResonse.fail("亮度修改失败");
    }
}
