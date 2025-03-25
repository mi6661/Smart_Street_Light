package org.example.application.repository;


import org.example.application.dao.LightInfo;
import org.example.application.entity.StreetLight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LightRepository {
    public List<StreetLight> getAllLights();
    public LightInfo getLightInfo(int id);
    public int updateLight(LightInfo lightInfo);
    public int addLight(LightInfo lightInfo);

    public boolean updateStatus(int id, int status);
    public boolean updateBrightness(int id, int brightness);
}
