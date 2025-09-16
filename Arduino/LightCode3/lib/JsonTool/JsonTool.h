#ifndef JSON_TOOL_H
#define JSON_TOOL_H
#include <ArduinoJson.h>
// Json工具类
class JsonTools {
    private:
        String jsonString;
        JsonDocument doc;
        DeserializationError error;
    public:
        bool setJsonString(String jsonString);
        String getLocation();
        String getLightStatus();
        int getBrightness();
        String getAuto();
    };
#endif