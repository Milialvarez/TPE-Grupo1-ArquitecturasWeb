package org.example.reportsmicroservice.services;

import org.example.reportsmicroservice.entities.ReporteFacturacion;
import org.example.reportsmicroservice.feignClients.BillingFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportsService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BillingFeignClient billingFeignClient;


    public Optional<ReporteFacturacion> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end){
        try {
            double totalBilled = (Double) this.billingFeignClient.getTotalBilled(origin, end).getBody();
            return Optional.of(new ReporteFacturacion("Reporte de facturacion", "Ganancias hechas en los viajes entre fechas.", totalBilled, origin, end));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}