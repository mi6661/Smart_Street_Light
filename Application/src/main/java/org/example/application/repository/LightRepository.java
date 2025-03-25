package org.example.application.repository;


import org.example.application.dao.LightInfo;
import org.example.application.entity.StreetLight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LightRepository {
    public List<StreetLight> getAllLights();
}
