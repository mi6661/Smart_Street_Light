package com.example.gwen.entity;

public class Light {
    public int id;
    public String location;
    public String status;
    public int brightness;
    public String auto;

    public Light(int id,String location,String status,int brightness,String auto){
        this.id = id;
        this.location = location;
        this.status = status;
        this.brightness = brightness;
        this.auto = auto;
    }


    @Override
    public String toString() {
        return "Light [id=" + id + ", location=" + location + ", status=" + status + ", brightness=" + brightness
                + ", auto=" + auto + "]";
    }
    //生成json文件格式
    public String getJson(){
        return "{" +
                "\"id\":" + id + "," +
                "\"location\":\"" + location + "\"," +
                "\"status\":\"" + status + "\"," +
                "\"brightness\":" + brightness + "," +
                "\"auto\":\"" + auto + "\"" + "}";
    }

    //提供一个模板
    public static Light getLightTemplate(){
        return new Light(1, "沈阳市", "on", 67, "off");
    }
}
