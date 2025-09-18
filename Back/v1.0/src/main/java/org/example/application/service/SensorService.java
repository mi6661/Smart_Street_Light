package org.example.application.service;


import org.example.application.dao.LightWithLastSensorData;
import org.example.application.dao.SensorDao;
import org.example.application.entity.SensorData;
import org.example.application.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    //传感器数据缓存
    List<SensorDao> sensorCache = new ArrayList<>();

    //上传更新信息到数据库
    public boolean insertData(SensorDao dao){
        return sensorRepository.insert(dao);
    }

    //缓存触感起数据
    public boolean cacheSensorData(SensorDao dao){
        if(sensorCache.size() >= 100){
            //把数据更新到数据库
            try {
                for (SensorDao sensorDao : sensorCache) {
                    sensorRepository.insert(sensorDao);
                }
                sensorCache.clear();
                sensorCache.add(dao);
                return true;
            } catch (RuntimeException e) {
                System.out.println("传感器数据上出数据库失败：" + e.getMessage());
                return false;
            }
        }else {
            try{
                sensorCache.add(dao);
                return true;
            }catch (Exception e){
                System.out.println("传感器缓存数据添加失败： "+e.getMessage());
                return false;
            }
        }
    }

    //获取所有所有路灯的传感器的所有数据
    public List<SensorData> getAllData(){
        List<SensorData> sensors = sensorRepository.getSensors();
        return sensors.isEmpty() ? null : sensors;
    }

    //获取所有通过light_id获取这个路灯传感器的所有数据
    public List<SensorData> getAllDataByLightId(int light_id){
        List<SensorData> sensors = sensorRepository.getSensors();
        List<SensorData> aimData = new ArrayList<SensorData>();
        for(SensorData sensor : sensors){
            if(sensor.light_id==light_id){
                aimData.add(sensor);
            }
        }
        return aimData;
    }

    //后去指定路灯id的最新数据
    public SensorData getAllLightRealTimeData(int light_id){
        return sensorRepository.getRealTimeSensorData(light_id);
    }
}
