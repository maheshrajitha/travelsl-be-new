package com.travelsl.travelsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class TravelslApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelslApplication.class, args);
	}

}
