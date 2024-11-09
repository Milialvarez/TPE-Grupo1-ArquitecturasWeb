package org.example.adminmicroservice.services;

import org.example.adminmicroservice.feignClients.MonopatinFeignClient;
import org.example.adminmicroservice.feignClients.UserFeignClient;
import org.example.adminmicroservice.models.Monopatin;
import org.example.adminmicroservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    RestTemplate restTemplate;

    UserFeignClient userFeignClient;
    MonopatinFeignClient monopatinFeignClient;

    public List<User> getAdmins() {
        return restTemplate.getForObject("http://localhost:8001/users", List.class);
    }

    public List<Monopatin> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        ResponseEntity<?> monopatins = this.monopatinFeignClient.getMonopatinesPorViajesPorAnio(anio, xViajes);
        return (List<Monopatin>) monopatins;
    }

    @PutMapping("/null")
    public ResponseEntity<?> anullateAccount(@RequestBody User user){
        return this.userFeignClient.anullateAccount(user);
    }
}
