package org.example.application.dao;

import org.example.application.entity.SensorData;
import org.example.application.entity.StreetLight;

import java.util.List;

public class LightWithAllSensorData {
    public StreetLight light;
    public List<SensorData> data;
    public LightWithAllSensorData(StreetLight light, List<SensorData> data) {
        this.light = light;
        this.data = data;
    }
}
