package org.example.adminmicroservice.services;

import org.example.adminmicroservice.feignClients.UserFeignClient;
import org.example.adminmicroservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    UserFeignClient userFeignClient;

    public List<User> getAdmins() {
        return restTemplate.getForObject("http://localhost:8001/users", List.class);
    }
}
