package org.example.billingmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BillingMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BillingMicroserviceApplication.class, args);
	}
}