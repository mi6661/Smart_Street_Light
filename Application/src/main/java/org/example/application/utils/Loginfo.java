package org.example.application.utils;

import java.util.Date;

public class Loginfo {

    //控制台输出日志
    public static String PART_USER = "user";
    public static String PART_LIGHT = "light";
    public static String PART_SENSOR_DATA = "sensor_data";
    public static String PART_CONTROL_LOG = "control_log";

    public static void show(String part,String message){
        Date date = new Date();
        String desc = String.format("Time:%s // Part:%s // Message:%s", date,part,message);
    }
}
