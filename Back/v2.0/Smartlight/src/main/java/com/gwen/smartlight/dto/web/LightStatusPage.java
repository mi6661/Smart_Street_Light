package com.gwen.smartlight.dto.web;


import lombok.Data;

import java.util.List;

/*路灯状态数据页面*/
@Data
public class LightStatusPage {
    /*地区*/
    String district;
    /*路灯状态列表*/
    List<LightInfo> infos;
}
