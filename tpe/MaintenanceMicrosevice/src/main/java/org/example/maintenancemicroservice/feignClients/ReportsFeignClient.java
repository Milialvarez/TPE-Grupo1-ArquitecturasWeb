package org.example.maintenancemicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(name="ReportsMicroservice", url="http://localhost:8010/reports")
public interface ReportsFeignClient {

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<?> getTotalBilled(LocalDate origin, LocalDate end);

    @GetMapping("/usoMonopatinesKm/{kilometros}")
    ResponseEntity<?> getReporteUsoMonopatinKm(@PathVariable(value = "kilometros") float km);

    @GetMapping("/usoMonopatinesTiempo/{tiempo}/{pausa}")
    ResponseEntity<?> getReporteUsoMonopatinTiempoPausa(@PathVariable(value = "tiempo") float t, @PathVariable(value = "pausa", required = false) boolean p);
}