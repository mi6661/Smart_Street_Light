package com.gwen.smartlight.controller;

import com.gwen.smartlight.dto.web.SensorAvgData;
import com.gwen.smartlight.dto.web.SensorDataOnDetailCard;
import com.gwen.smartlight.dto.web.SensorRealTimeData;
import com.gwen.smartlight.dto.web.SensorsTempNow;
import com.gwen.smartlight.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensor")
public class WebSensorDataController {
    @Autowired
    private SensorService sensorService;

    /*通过路灯id，返回最近50条传感器数据记录*/
    @GetMapping("/list")
    List<SensorDataOnDetailCard> getSensorData(@RequestParam int id,@RequestParam int amount) {
        if(id<=0 || amount <=0) return null;
        if(amount < 1000){
            return sensorService.getSensorDataListOnDetailCardsById(id,amount);
        }else{
            return sensorService.getSensorDataListOnDetailCardsById(id,100);
        }
    }

    /*
     * @param null
     * @return 获取所有路灯的最新的集合状态
     */
    @GetMapping("light_temp_now")
    List<SensorsTempNow> getSensorsTempList(){
        return sensorService.getSensorDataNowById();
    }

    /*
    * 传感器数据统计数据
    * */
    @GetMapping("/analysis")
    public Map<String, Object> getSensorAnalysis() {
        Map<String, Object> result = new HashMap<>();
        result.put("avgTemperature", 24.3);
        result.put("avgHumidity", 66.8);
        result.put("avgWindSpeed", 4.7);
        result.put("avgPm25", 81.2);

        result.put("dailyTemperature", List.of(
                Map.of("date", "2025-09-25", "avgTemp", 22.1),
                Map.of("date", "2025-09-26", "avgTemp", 24.5),
                Map.of("date", "2025-09-27", "avgTemp", 23.9)
        ));

        result.put("brightnessDistribution", List.of(
                Map.of("range", "0-500", "count", 4),
                Map.of("range", "500-1000", "count", 8),
                Map.of("range", "1000-1500", "count", 12),
                Map.of("range", "1500-2000", "count", 6)
        ));
        return result;
    }


    /*获取每个路灯实时的温度，湿度，风速*/
    @GetMapping("/realtime_data")
    List<SensorRealTimeData> getAllSensorRealTimeData() {
        return sensorService.getAllSensorRealTimeData();
    }

    /*获取每天都平局温度，湿度，风速*/
    @GetMapping("/avgdata")
    List<SensorAvgData> getAllSensorAvgData() {
        return sensorService.getAllSensorAvgData();
    }
}
