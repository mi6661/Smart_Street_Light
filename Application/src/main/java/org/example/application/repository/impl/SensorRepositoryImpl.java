package org.example.application.repository.impl;


import org.example.application.dao.SensorDao;
import org.example.application.entity.SensorData;
import org.example.application.repository.SensorRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SensorRepositoryImpl implements SensorRepository {


    private final JdbcTemplate jdbcTemplate;

    public SensorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //添加数据
    @Override
    public boolean insert(SensorDao dao) {
        String sql = "INSERT INTO sensor_data(light_id,temperature,humidity,pm24) VALUE(?,?,?,?)";
        int count = jdbcTemplate.update(sql, new Object[] {dao.light_id,dao.temperature,dao.humidity,dao.pm24});
        return count > 0;
    }

    //查询所有数据
    @Override
    public List<SensorData> getSensors() {
        String sql = "SELECT * FROM sensor_data";
        List<SensorData> list = new ArrayList<SensorData>();
        list.addAll(jdbcTemplate.query(sql, new RowMapper<SensorData>() {
            @Override
            public SensorData mapRow(ResultSet rs, int rowNum) throws SQLException {
                SensorData data = new SensorData();
                data._id = rs.getInt(1);
                data.light_id = rs.getInt(2);
                data.temperature = rs.getInt(3);
                data.humidity = rs.getInt(4);
                data.pm24 = rs.getInt(5);
                data.create_time = rs.getDate(6);
                return data;
            }
        }));
        return list;
    }

    @Override
    public SensorData getRealTimeSensorData(int id) {
        String sql = "select * from sensor_data  where light_id = ? order by create_time desc limit 1";
        List<SensorData> query = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<SensorData>() {

            @Override
            public SensorData mapRow(ResultSet rs, int rowNum) throws SQLException {
                SensorData data = new SensorData();
                data._id = rs.getInt(1);
                data.light_id = rs.getInt(2);
                data.temperature = rs.getInt(3);
                data.humidity = rs.getInt(4);
                data.pm24 = rs.getInt(5);
                data.create_time = rs.getDate(6);
                return data;
            }
        });
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }
}
