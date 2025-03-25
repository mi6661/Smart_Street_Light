package org.example.application.controller;


import org.example.application.dao.LightInfo;
import org.example.application.entity.StreetLight;
import org.example.application.repository.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/light")
public class LightRequest {

    @Autowired
    private LightRepository lightRepository;

    @GetMapping("/test")
    public List<StreetLight> test(){
        return lightRepository.getAllLights();
    }

    @PostMapping("/addLight")
    public StreetLight addLight(@RequestBody LightInfo light){

    }

}
