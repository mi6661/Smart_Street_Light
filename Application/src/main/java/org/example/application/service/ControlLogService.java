package org.example.application.service;

import org.example.application.dao.LogDao;
import org.example.application.entity.ControlLog;
import org.example.application.repository.ControlLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ControlLogService {
    @Autowired
    private ControlLogRepository controlLogRepository;

    //更新控制记录
    public boolean update(int user_id,int light_id,int action,int value) {
        return controlLogRepository.insert(new LogDao(user_id,light_id,action,value));
    }

    //查询所有控制记录
    public List<ControlLog> allLog(){
        return controlLogRepository.findAll();
    }

    //通过用户id查询控制记录
    public List<ControlLog> LogsByUserId(int user_id){
        List<ControlLog> all = controlLogRepository.findAll();
        List<ControlLog> logs = new ArrayList<ControlLog>();
        for(ControlLog log : all){
            if(log.user_id==user_id){
                logs.add(log);
            }
        }
        return logs;
    }

    //通过路灯id查询控制记录
    public List<ControlLog> LogsByLightId(int light_id){
        List<ControlLog> all = controlLogRepository.findAll();
        List<ControlLog> logs = new ArrayList<ControlLog>();
        for(ControlLog log : all){
            if(log.light_id==light_id){
                logs.add(log);
            }
        }
        return logs;
    }
}
