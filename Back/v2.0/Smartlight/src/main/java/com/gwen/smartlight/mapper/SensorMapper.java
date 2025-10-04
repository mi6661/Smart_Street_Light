package com.gwen.smartlight.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gwen.smartlight.dto.web.SensorAvgData;
import com.gwen.smartlight.dto.web.SensorRealTimeData;
import com.gwen.smartlight.entity.Sensor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SensorMapper extends BaseMapper<Sensor> {
    List<SensorRealTimeData> getAllSensorRealTimeData();
    List<SensorAvgData> getAllSensorAvgData();
}
