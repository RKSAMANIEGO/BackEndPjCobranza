package com.example.demo;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class PjCobranzaBackApplication  {

	public static void main(String[] args) {
		//SpringApplication.run(PjCobranzaBackApplication.class, args);

	    SpringApplication app = new SpringApplication(PjCobranzaBackApplication.class);
	    app.setDefaultProperties(Collections.singletonMap("server.port", System.getenv("PORT")));
        	app.run(args);
	}

}
