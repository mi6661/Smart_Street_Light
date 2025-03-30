package org.example.application.service;


import org.example.application.dao.LightInfo;
import org.example.application.entity.StreetLight;
import org.example.application.repository.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LightService {

    @Autowired
    private LightRepository lightRepository;
    //返回所以路灯信息
    public List<StreetLight> getAllLights() {
        return lightRepository.getAllLights();
    }
    //通过id查询路灯信息
    public LightInfo getLightInfo(int id){
        return lightRepository.getLightInfo(id);
    }

    //添加路灯
    public boolean addLight(LightInfo light) {
        return lightRepository.addLight(light)==1;
    }

    //修改路灯位置信息
    public boolean updateLocation(int id,String location){
        LightInfo lightInfo = getLightInfo(id);
        if(lightInfo != null){
            lightInfo.location = location;
            return lightRepository.updateLight(lightInfo)==1;
        }
        return false;
    }

    //更新路灯状态
    public boolean update(LightInfo light){
        return lightRepository.updateLight(light)==1;
    }

}
