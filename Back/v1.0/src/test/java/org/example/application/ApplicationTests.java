package org.example.application;

import org.example.application.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.Socket;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads(){
		try{
			Socket socket = new Socket("127.0.0.1", 12334);
			socket.getOutputStream().write("hello".getBytes("UTF-8"));
		}catch (IOException e){
			e.printStackTrace();
		}
	}

}
