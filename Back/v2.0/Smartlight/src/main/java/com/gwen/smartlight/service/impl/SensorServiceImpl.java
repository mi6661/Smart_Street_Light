package com.gwen.smartlight.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gwen.smartlight.dto.hard.SensorInfo;
import com.gwen.smartlight.dto.web.SensorDataOnDetailCard;
import com.gwen.smartlight.entity.Sensor;
import com.gwen.smartlight.mapper.SensorMapper;
import com.gwen.smartlight.service.SensorService;
import com.gwen.smartlight.utils.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorMapper sensorMapper;

    //更新硬件上传的数据
    @Override
    public boolean HardDataUpdate(SensorInfo sensorInfo) {
        Sensor sensor = new Sensor();
        sensor.light_id = sensorInfo.getLightId();
        sensor.temperature = sensorInfo.getTemp();
        sensor.humidity = sensorInfo.getHumi();
        sensor.brightness = sensorInfo.getBrightness();
        sensor.wind_speed = sensorInfo.getWind_speed();
        sensor.create_time = DateTools.getCurrentTimestamp();

        return sensorMapper.insert(sensor) > 0;
    }

    /*
    * @param 路灯编号id,记录数据n
    * @return 最新n条记录
    * */
    @Override
    public List<SensorDataOnDetailCard> getSensorDataListOnDetailCardsById(int id,int amount) {
        QueryWrapper<Sensor> queryWrapper = new QueryWrapper<Sensor>();
        queryWrapper.eq("light_id", id);
        queryWrapper.orderByDesc("create_time");
        queryWrapper.last("limit "+amount);
        List<Sensor> sensors = sensorMapper.selectList(queryWrapper);

        List<SensorDataOnDetailCard> sensorDataOnDetailCards = new ArrayList<>();
        sensors.forEach(sensor->{
            SensorDataOnDetailCard card = new SensorDataOnDetailCard();
            card.setId(sensor.getId());
            card.setTemperature(sensor.getTemperature());
            card.setHumidity(sensor.getHumidity());
            card.setWind_speed(sensor.getWind_speed());
            card.setPm25(sensor.getPm25());
            card.setCreate_time(sensor.getCreate_time());
            sensorDataOnDetailCards.add(card);
        });

        return sensorDataOnDetailCards;
    }


}
