package com.gwen.smartlight.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gwen.smartlight.dto.hard.LightStatus;
import com.gwen.smartlight.dto.web.LightInfo;
import com.gwen.smartlight.dto.web.LightStatusPage;
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
        return getLightInfos(lights);
    }

    @Override
    public List<String> getDistricts() {
        List<Light> districts = lightMapper.selectList(new QueryWrapper<Light>().select("distinct district"));
        List<String> lists_district = new ArrayList<>();
        districts.forEach(district->{
            lists_district.add(district.getDistrict());
        });
        return lists_district;
    }

    @Override
    public boolean updateLight(LightInfo info) {
        try {
            UpdateWrapper<Light> updateWrapper = new UpdateWrapper<Light>();
            updateWrapper.eq("id", info.getId());
            updateWrapper.set("location", info.getLocation());
            updateWrapper.set("status", info.getStatus());
            updateWrapper.set("brightness", info.getBrightness());
            updateWrapper.set("auto", info.getAuto());
            lightMapper.update(updateWrapper);
            return true;
        } catch (RuntimeException e) {
            System.err.println("路灯信息更新失败："+e.getMessage());
            return false;
        }
    }


    /**
     * @param district:地区名
     * @return List<LightInfo>: 符合条件的路灯信息集合
     * */
    @Override
    public List<LightInfo> getLightStatusByDistrict(String district) {

        QueryWrapper<Light> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("district", district);
        List<Light> lights = lightMapper.selectList(queryWrapper);

        return getLightInfos(lights);
    }


    /**
     * 从Light列表 中提取出 LightInfo列表
     * */
    private List<LightInfo> getLightInfos(List<Light> lights) {
        List<LightInfo> infos = new ArrayList<>();
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
