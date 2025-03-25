package org.example.application.repository.impl;

import org.example.application.dao.LightInfo;
import org.example.application.entity.StreetLight;
import org.example.application.repository.LightRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class LightRepositoryImpl implements LightRepository {


    private final JdbcTemplate jdbcTemplate;

    public LightRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //查询出所有路灯的信息
    @Override
    public List<StreetLight> getAllLights() {
        String sql = "select * from street_lights";
        List<StreetLight> result = jdbcTemplate.query(sql, new RowMapper<StreetLight>() {
            @Override
            public StreetLight mapRow(ResultSet rs, int rowNum) throws SQLException {
                StreetLight light = new StreetLight();
                light._id = rs.getInt(1);
                light.location = rs.getString(2);
                light.status = rs.getInt(3);
                light.brightness = rs.getInt(4);
                light.create_time = rs.getDate(5);
                return light;
            }
        });
        return result;
    }
}
