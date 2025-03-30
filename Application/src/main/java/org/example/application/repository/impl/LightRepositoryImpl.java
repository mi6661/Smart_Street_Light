package org.example.application.repository.impl;

import org.example.application.dao.LightInfo;
import org.example.application.entity.StreetLight;
import org.example.application.repository.LightRepository;
import org.example.application.response.ApiResonse;
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
                light.status = rs.getString(3);
                light.brightness = rs.getInt(4);
                light.auto = rs.getString(5);
                light.create_time = rs.getDate(6);
                return light;
            }
        });
        return result;
    }

    //通过路灯id,查找路灯信息
    public LightInfo getLightInfo(int id) {
        String sql = "select * from street_lights where _id=?";
        List<LightInfo> list = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<LightInfo>() {
            @Override
            public LightInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                LightInfo lightInfo = new LightInfo();
                lightInfo.id = rs.getInt(1);
                lightInfo.location = rs.getString(2);
                lightInfo.status = rs.getString(3);
                lightInfo.brightness = rs.getInt(4);
                lightInfo.auto = rs.getString(5);
                return lightInfo;
            }
        });
        //这里id是唯一的，所以list只有一个对象
        try{
            return list.get(0);
        }catch (Exception e){
            //id不存在，返回空值
            return null;
        }
    }

    @Override
    //更新路灯状态
    public int updateLight(LightInfo lightInfo) {
        String sql = "UPDATE street_lights " +
                     "SET location = ?  , status = ? , brightness = ? , auto = ?" +
                     "WHERE _id = ?";
        return jdbcTemplate.update(sql, new Object[]{lightInfo.location,lightInfo.status,lightInfo.brightness,lightInfo.auto,lightInfo.id});
    }

    @Override
    public int addLight(LightInfo lightInfo) {
        String sql = "INSERT INTO street_lights (location,`status`,brightness,auto) VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[]{lightInfo.location, lightInfo.status, lightInfo.brightness , lightInfo.auto});
    }
}
