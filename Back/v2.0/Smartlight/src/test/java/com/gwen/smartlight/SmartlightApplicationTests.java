package com.gwen.smartlight;

import com.gwen.smartlight.dto.web.SensorAvgData;
import com.gwen.smartlight.dto.web.SensorsTempNow;
import com.gwen.smartlight.entity.Light;
import com.gwen.smartlight.mapper.LightMapper;
import com.gwen.smartlight.mapper.SensorMapper;
import com.gwen.smartlight.service.SensorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SmartlightApplicationTests {

    @Autowired
    private LightMapper lightMapper;

    @Autowired
    private SensorService sensorService;
    @Autowired
    private SensorMapper sensorMapper;

    @Test
    void contextLoads() {
        List<SensorAvgData> list = sensorMapper.getAllSensorAvgData();
        System.out.println(list);
    }
}
