package org.example.application.dao;

import org.example.application.entity.SensorData;
import org.example.application.entity.StreetLight;

public class LightWithLastSensorData {
    public StreetLight light;
    public SensorData sensorData;
    public LightWithLastSensorData(StreetLight light, SensorData sensorData) {
        this.light = light;
        this.sensorData = sensorData;
    }
}
