package com.minsait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceTrainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTrainerApplication.class, args);
	}

}
