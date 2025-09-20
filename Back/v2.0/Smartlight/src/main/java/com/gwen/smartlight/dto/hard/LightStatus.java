package com.gwen.smartlight.dto.hard;


import lombok.Data;

/*路灯状态类
* 0-关闭
* 1-开启
* */
@Data
public class LightStatus {
    /*路灯是否开关*/
    int status;
    /*路灯是否自动*/
    int auto;
}
