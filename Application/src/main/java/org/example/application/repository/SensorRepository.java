package org.example.application.repository;

import org.example.application.dao.SensorDao;
import org.example.application.entity.SensorData;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SensorRepository {
    public boolean insert(SensorDao dao);
    public List<SensorData> getSensors();
}
