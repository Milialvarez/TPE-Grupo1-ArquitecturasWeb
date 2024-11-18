package org.example.adminmicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name="ReportsMicroservice", url="http://localhost:8010/reports")
public interface ReportsFeignClient {

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate origin, @PathVariable("fechaFin") LocalDate end);

    @GetMapping("/activosVsMantenimiento")
    ResponseEntity<?> getReporteMonopatinesActivosVsMantenidos();
}