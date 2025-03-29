package org.example.application.repository;


import org.example.application.dao.LogDao;
import org.example.application.entity.ControlLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlLogRepository {
    public boolean insert(LogDao logDao);
    public List<ControlLog> findAll();
}
