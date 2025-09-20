package com.gwen.smartlight.utils;

import java.sql.Timestamp;

public class DateTools {
    //获取当前时间戳
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
