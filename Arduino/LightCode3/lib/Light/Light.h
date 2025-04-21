#ifndef LIGHT_H
#define LIGHT_H
#include <ArduinoJson.h>
// light类
class Light {
    private:
        int id;
        String location;
        String status;
        int brightness;
        String Auto;
        float humi;
        float temp;
        float lightIntensity;
        StaticJsonDocument<200> doc;
    public:
        //structor
        Light(int id, String location, String status, int brightness, String Auto);
        //setter
        void setLocation(String location);
        void setStatus(String status);
        void setBrightness(int brightness);
        void setAuto(String Auto);
        void setHumi(float humi);
        void setTemp(float temp);
        void setLightIntensity(float lightIntensity);
        //getter
        int getId();
        String getLoaction();
        String getStatus();
        int getBrightness();
        String getAuto();
        float getLightIntensity();
        //get Json
        String getJsonString();
    };
#endif