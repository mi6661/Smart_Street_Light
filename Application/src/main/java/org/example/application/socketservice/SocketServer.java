package org.example.application.socketservice;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
* 因为需要使用socket实现与硬件的高频率的信息交互
* 所以这里需要使用TCP/IP来进行通信。这里如果使用
* API来进行HTTP通信是完全满足不了要求的。
* */


//这里使用了@Service注解来让Springboot帮我们自动实现SocketServer类。
@Service
public class SocketServer {
    private ServerSocket serverSocket;
    int port;

    //在构造时，通过@Value来自动注入参数。
    public SocketServer(@Value("${socket.port}")int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.port = port;
        System.out.println("Server started at port " + port);
    }


    //在构造好后，springboot会自动帮我们调用这个start()来帮我们启动这个服务。
    @PostConstruct
    public void start(){
        new Thread(() -> {
            try{
                System.out.println("Waiting for connection at port"+port+" ...");
                while(true){
                    Socket socket = serverSocket.accept();

                    InputStream inputStream = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

}
