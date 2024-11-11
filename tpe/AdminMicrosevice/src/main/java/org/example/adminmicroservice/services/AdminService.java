package org.example.adminmicroservice.services;

import org.example.adminmicroservice.feignClients.MonopatinFeignClient;
import org.example.adminmicroservice.feignClients.ReportsFeignClient;
import org.example.adminmicroservice.feignClients.AccountsFeignClient;
import org.example.adminmicroservice.models.Account;
import org.example.adminmicroservice.models.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AccountsFeignClient accountsFeignClient;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;
    @Autowired
    ReportsFeignClient reportsFeignClient;

    public List<Monopatin> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        ResponseEntity<?> monopatins = this.monopatinFeignClient.getMonopatinesPorViajesPorAnio(anio, xViajes);
        return (List<Monopatin>) monopatins;
    }

    @PutMapping("/null")
    public ResponseEntity<?> anullateAccount(@RequestBody Account ac){
        return this.accountsFeignClient.anullateAccount(ac);
    }

    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end){
        try {
            return this.reportsFeignClient.getTotalBilled(origin, end);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
