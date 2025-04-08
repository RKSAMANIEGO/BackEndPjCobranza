package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class PjCobranzaBackApplication  {

	public static void main(String[] args) {
		SpringApplication.run(PjCobranzaBackApplication.class, args);

		SpringApplication app = new SpringApplication(Application.class);
        	app.setDefaultProperties(Collections.singletonMap("server.port", System.getenv("PORT")));
        	app.run(args);
	}

}
