package http;

import java.util.Date;

public class TimeUtil {
    public static String getTime(){
        return (new Date()).toString();
    }
}
