package org.example.application.service;


import org.example.application.dao.LightInfo;
import org.example.application.dao.LightWithAllSensorData;
import org.example.application.dao.LightWithLastSensorData;
import org.example.application.entity.SensorData;
import org.example.application.entity.StreetLight;
import org.example.application.repository.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LightService {

    @Autowired
    private LightRepository lightRepository;
    @Autowired
    private SensorService sensorService;
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

    //获取全部与路灯相关联的传感器数据
    public List<LightWithAllSensorData> getAllLightsWithSensorData(){
        List<StreetLight> lights = getAllLights();
        List<LightWithAllSensorData> unionData = new ArrayList<>();
        for(StreetLight light : lights){
            int light_id = light._id;
            List<SensorData> data = sensorService.getAllDataByLightId(light_id);
            LightWithAllSensorData lsd = new LightWithAllSensorData(light,data);
            unionData.add(lsd);
        }
        return unionData;
    }

    //获取与所有路灯当前的实时传感器数据
    public List<LightWithLastSensorData> getAllLightsWithLastSensorData(){
        List<StreetLight> lights = getAllLights();
        List<LightWithLastSensorData> unionData = new ArrayList<>();
        for(StreetLight light : lights){
            int light_id = light._id;
            SensorData TimeData = sensorService.getAllLightRealTimeData(light_id);
            LightWithLastSensorData lws = new LightWithLastSensorData(light,TimeData);
            unionData.add(lws);
        }
        return unionData;
    }

}
