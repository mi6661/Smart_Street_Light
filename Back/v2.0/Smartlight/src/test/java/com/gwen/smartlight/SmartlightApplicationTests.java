package com.gwen.smartlight;

import com.gwen.smartlight.entity.Light;
import com.gwen.smartlight.mapper.LightMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartlightApplicationTests {

    @Autowired
    private LightMapper lightMapper;

    @Test
    void contextLoads() {
        Light light = lightMapper.selectById(1);
        System.out.println(light.getLocation());
    }

}
