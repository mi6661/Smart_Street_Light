package com.gwen.smartlight.service;

import com.gwen.smartlight.dto.hard.SensorInfo;
import com.gwen.smartlight.dto.web.SensorsTempNow;
import com.gwen.smartlight.dto.web.SensorDataOnDetailCard;

import java.util.List;

public interface SensorService {
    boolean HardDataUpdate(SensorInfo sensorInfo);
    List<SensorDataOnDetailCard> getSensorDataListOnDetailCardsById(int id,int amount);


    List<SensorsTempNow> getSensorDataNowById();
}
