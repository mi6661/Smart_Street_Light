package org.example.application.controller;


import org.example.application.entity.ControlLog;
import org.example.application.response.ApiResonse;
import org.example.application.service.ControlLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlLog")
public class ControlLogRequest {
    @Autowired
    private ControlLogService controlLogService;

    //查询所有控制记录
    @GetMapping("/all")
    public ApiResonse<List<ControlLog>> getAll() {
        return ApiResonse.success(controlLogService.allLog());
    }

    //通过用户id查询控制记录
    @PostMapping("/user_id")
    public ApiResonse<List<ControlLog>> getLogsByUserId(@RequestParam int user_id) {
        List<ControlLog> controlLogs = controlLogService.LogsByUserId(user_id);
        return controlLogs.isEmpty() ? ApiResonse.fail("没有该用户的数据") : ApiResonse.success(controlLogs);
    }

    //通过路灯id查询控制记录
    @PostMapping("/light_id")
    public ApiResonse<List<ControlLog>> getLogsByLightId(@RequestParam int light_id) {
        List<ControlLog> controlLogs = controlLogService.LogsByLightId(light_id);
        return controlLogs.isEmpty() ? ApiResonse.fail("没有该路灯的信息") : ApiResonse.success(controlLogs);
    }

}
