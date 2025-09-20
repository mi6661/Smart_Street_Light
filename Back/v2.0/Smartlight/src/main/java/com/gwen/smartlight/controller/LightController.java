package com.gwen.smartlight.controller;

import com.gwen.smartlight.dto.web.LightInfo;
import com.gwen.smartlight.entity.Light;
import com.gwen.smartlight.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
