package org.example.monopatinmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MonopatinMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MonopatinMicroserviceApplication.class, args);
	}
}