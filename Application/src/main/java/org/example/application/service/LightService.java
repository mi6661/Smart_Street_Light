package org.example.application.service;


import org.example.application.entity.StreetLight;
import org.example.application.repository.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LightService {

    @Autowired
    private LightRepository lightRepository;

    public List<StreetLight> getAllLights() {
        return getAllLights();
    }

}
