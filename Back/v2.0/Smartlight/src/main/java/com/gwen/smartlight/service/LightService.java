package com.gwen.smartlight.service;

import com.gwen.smartlight.dto.LightStatus;
import com.gwen.smartlight.dto.web.LightInfo;

import java.util.List;

public interface LightService {
    LightStatus getLightStatusById(int id);
    List<LightInfo> getLightInfos();
}
