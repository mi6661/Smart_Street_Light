package com.gwen.smartlight.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gwen.smartlight.dto.LightStatus;
import com.gwen.smartlight.dto.web.LightInfo;
import com.gwen.smartlight.entity.Light;
import com.gwen.smartlight.mapper.LightMapper;
import com.gwen.smartlight.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LightServiceImpl implements LightService {

    @Autowired
    private LightMapper lightMapper;

    /*通过ID查询路灯的开关状态和自动状态*/
    @Override
    public LightStatus getLightStatusById(int id) {
        Light light;
        if((light = lightMapper.selectById(id))!=null) {
            LightStatus status = new LightStatus();
            status.setStatus(light.getStatus().equals("on")?1:0);
            status.setAuto(light.getAuto().equals("on")?1:0);
           return status;
        }
        return null;//查询不到，返回空
    }

    /*
    * @param null
    * @return 返回数据库中所有的路灯信息
    * */
    @Override
    public List<LightInfo> getLightInfos() {
        List<Light> lights = lightMapper.selectList(new QueryWrapper<Light>().orderByAsc("id"));
        List<LightInfo> infos = new ArrayList<LightInfo>();
        lights.forEach(light->{
            LightInfo info = new LightInfo();
            info.setId(light.getId());
            info.setLocation(light.getLocation());
            info.setStatus(light.getStatus());
            info.setBrightness(light.getBrightness());
            info.setAuto(light.getAuto());
            infos.add(info);
        });
        return infos;
    }
}
