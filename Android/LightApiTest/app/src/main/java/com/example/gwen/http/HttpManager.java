package com.example.gwen.http;

import com.example.gwen.entity.Light;
import com.example.gwen.utils.TimeUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {
    String baseUrl;

    public HttpManager(String baseUrl){
        this.baseUrl = baseUrl;
    }


    //通过id获取路灯状态信息
    public String getLightById(int id){
        StringBuffer response = new StringBuffer();
        try{
            URL url = new URL(baseUrl + "/light/list");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            System.out.println(url);
            InputStream inputStream = connection.getInputStream();
            InputStreamReader isreader = new InputStreamReader(inputStream);
            BufferedReader bfreader = new BufferedReader(isreader);

            String line;
            while((line = bfreader.readLine())!=null){
                response.append(line);
            }
            System.out.println(response.toString());
            return response.toString();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("[ERROR]"+ TimeUtil.getTime()+"__"+e.getMessage()+":HttpManager.getLightById");
            return null;
        }
    }

    //跟新路灯状态信息
    public boolean setLight(Light light){
        StringBuffer response = new StringBuffer();
        String urlString = baseUrl+"/light/update";                         
        String post_body =  light.getJson();
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json");
            //这里设置权限，允许对服务器进行写入写出
            connection.setDoInput(true);
            connection.setDoOutput(true);    

            //写入数据
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(post_body.getBytes());
            outputStream.flush();
            outputStream.close();

            //读取响应数据
            int status = connection.getResponseCode();
            InputStream inputStream = status==200? connection.getInputStream() : connection.getErrorStream();
            InputStreamReader isreader = new InputStreamReader(inputStream);
            BufferedReader bfreader = new BufferedReader(isreader);
            String line;
            while ((line=bfreader.readLine())!=null) {
                response.append(line);
            }
            System.out.println("[SUCCESSFUL]"+TimeUtil.getTime()+":HttpManager.setLight "+response);
            return true;
        }catch(Exception e){
            System.out.println("[ERROR]"+TimeUtil.getTime()+":HttpManager.setLight");
            return false;
        }
    }
}
