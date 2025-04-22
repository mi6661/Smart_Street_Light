package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {
    String baseUrl;

    HttpManager(String baseUrl){
        this.baseUrl = baseUrl;
    }


    //通过id获取路灯状态信息
    public String getLightById(int id){
        StringBuffer response = new StringBuffer();
        try{
            String urlString = String.format(baseUrl+"/light/id?id=%d", id);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            InputStream inputStream = connection.getInputStream();

            InputStreamReader isreader = new InputStreamReader(inputStream);
            BufferedReader bfreader = new BufferedReader(isreader);

            String line;
            while((line = bfreader.readLine())!=null){
                response.append(line);
            }
            return response.toString();
        }catch(Exception e){
            System.out.println("[ERROR]"+TimeUtil.getTime()+":HttpManager.getLightById");
            return null;
        }
    }

    //跟新路灯状态信息
    public boolean setLight(){




        return true;
    }
}
