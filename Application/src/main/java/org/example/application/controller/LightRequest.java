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
        if(lightService.update(light)){
            return ApiResonse.success(true);
        }
        return ApiResonse.fail("更新失败");
    }
}
