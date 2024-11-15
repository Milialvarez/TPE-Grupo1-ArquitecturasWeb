package org.example.usermicroservice;

import jakarta.annotation.PostConstruct;
import org.example.usermicroservice.utils.DataLoaderHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
@EnableFeignClients
public class UserMicroserviceApplication {
	private final DataLoaderHelper dataLoaderHelper;

    public UserMicroserviceApplication(DataLoaderHelper dataLoaderHelper) {
        this.dataLoaderHelper = dataLoaderHelper;
    }

    public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
	@PostConstruct
	public void init() throws IOException, ParseException {
		dataLoaderHelper.loadAccounts("src/main/resources/accounts.csv");
		dataLoaderHelper.loadRoles("src/main/resources/roles.csv");
		dataLoaderHelper.loadUsers("src/main/resources/users.csv");
		dataLoaderHelper.loadUserAccounts("src/main/resources/user_accounts.csv");
	}
}
