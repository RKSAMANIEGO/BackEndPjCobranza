package com.example.demo;
//import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class PjCobranzaBackApplication extends SpringBootServletInitializer  {
	
	

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PjCobranzaBackApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(PjCobranzaBackApplication.class, args);
		
	}

}
