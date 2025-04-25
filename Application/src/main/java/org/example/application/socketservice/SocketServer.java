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


@Service
public class SocketServer {
    private ServerSocket serverSocket;

    int port;

    public SocketServer(@Value("${socket.port}")int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.port = port;
        System.out.println("Server started at port " + port);
    }

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
