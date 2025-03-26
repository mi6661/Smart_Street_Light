package org.example.application.service;


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


    public boolean insertData(SensorDao dao){
        return sensorRepository.insert(dao);
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




}
