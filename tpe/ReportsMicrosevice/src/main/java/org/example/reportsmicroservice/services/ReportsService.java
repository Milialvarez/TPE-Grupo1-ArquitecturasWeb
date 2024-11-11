package org.example.reportsmicroservice.services;

import org.example.adminmicroservice.feignClients.BillingFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsService {
    @Autowired
    RestTemplate restTemplate;

    BillingFeignClient billingFeignClient;


    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen")LocalDate origin,@PathVariable("fechaFin") LocalDate end){
        try {
            //aca tiene que hacerse el reporte con la plata recaudada entre ambas fechas
            return this.billingFeignClient.getTotalBilled(origin, end);
        } catch () {

        }
    }
}