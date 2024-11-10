package org.example.reportsmicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="BillingMicroservice", url="http://localhost:8003/bills")
public interface BillingFeignClient {

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") LocalDate d1, @PathVariable("fechaFin") LocalDate d2);

}