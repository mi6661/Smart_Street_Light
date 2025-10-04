package com.gwen.smartlight.service;

import com.gwen.smartlight.dto.hard.LightStatus;
import com.gwen.smartlight.dto.web.LightInfo;
import com.gwen.smartlight.dto.web.LightSite;
import com.gwen.smartlight.dto.web.LightStatusPage;

import java.util.List;

public interface LightService {
    LightStatus getLightStatusById(int id);
    List<LightInfo> getLightInfos();
    List<String> getDistricts();
    boolean updateLight(LightInfo info);
    List<LightInfo> getLightStatusByDistrict(String district);
    List<LightSite> getLightSites();
}
