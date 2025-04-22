import java.net.http.HttpClient;

import http.HttpManager;
import http.Light;
import http.TimeUtil;

public class App {
    public static void main(String[] args) throws Exception {
        Light light = Light.getLightTemplate();
        String baseUrl = "http://49.232.141.65:8081";
        HttpManager manager = new HttpManager(baseUrl);
        System.out.println(manager.getLightById(1));
    }

}
