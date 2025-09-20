package com.gwen.smartlight.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gwen.smartlight.entity.Light;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LightMapper extends BaseMapper<Light> {
    //自定义复杂查询

}
