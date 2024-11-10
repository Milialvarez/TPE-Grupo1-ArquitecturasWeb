package org.example.adminmicroservice.feignClients;

import org.example.adminmicroservice.models.Reporte;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="ReportsMicroservice", url="http://localhost:8001/reports")
public interface ReportsFeignClient {

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<?> getTotalBilled(LocalDate origin, LocalDate end);
}