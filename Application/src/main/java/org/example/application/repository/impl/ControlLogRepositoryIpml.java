package org.example.application.repository.impl;

import org.example.application.dao.LogDao;
import org.example.application.entity.ControlLog;
import org.example.application.repository.ControlLogRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ControlLogRepositoryIpml implements ControlLogRepository {


    private final JdbcTemplate jdbcTemplate;

    public ControlLogRepositoryIpml(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insert(LogDao logDao) {
        String sql = "insert into control_logs(user_id,light_id,action,`value`) values(?,?,?,?)";
        try {
            return 1==jdbcTemplate.update(sql, new Object[]{logDao.user_id, logDao.light_id, logDao.action, logDao.value});
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ControlLog> findAll() {
        List<ControlLog> logs = new ArrayList<ControlLog>();
        String sql = "select * from control_logs";
        logs = jdbcTemplate.query(sql, new RowMapper<ControlLog>() {
            @Override
            public ControlLog mapRow(ResultSet rs, int rowNum) throws SQLException {
                ControlLog log = new ControlLog();
                log._id = rs.getInt(1);
                log.user_id = rs.getInt(2);
                log.light_id = rs.getInt(3);
                log.action = rs.getInt(4);
                log.value = rs.getInt(5);
                log.create_time = rs.getDate(6);
                return log;
            }
        });
        return !logs.isEmpty() ? logs : null;
    }
}
