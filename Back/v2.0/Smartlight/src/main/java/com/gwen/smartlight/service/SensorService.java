package com.gwen.smartlight.service;

import com.gwen.smartlight.dto.hard.SensorInfo;
import com.gwen.smartlight.dto.web.SensorAvgData;
import com.gwen.smartlight.dto.web.SensorRealTimeData;
import com.gwen.smartlight.dto.web.SensorsTempNow;
import com.gwen.smartlight.dto.web.SensorDataOnDetailCard;

import java.util.List;

public interface SensorService {
    boolean HardDataUpdate(SensorInfo sensorInfo);
    List<SensorDataOnDetailCard> getSensorDataListOnDetailCardsById(int id,int amount);
    List<SensorsTempNow> getSensorDataNowById();
    /*获取所有路灯传感器的实时数据*/
    List<SensorRealTimeData> getAllSensorRealTimeData();
    List<SensorAvgData>  getAllSensorAvgData();
}
