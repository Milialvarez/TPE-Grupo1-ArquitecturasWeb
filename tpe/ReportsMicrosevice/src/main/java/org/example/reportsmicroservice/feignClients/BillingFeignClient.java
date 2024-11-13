package org.example.reportsmicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

<<<<<<< HEAD
import java.util.Date;

@FeignClient(name="BillingMicroservice", url="http://localhost:8005/bills")
=======
import java.time.LocalDate;

@FeignClient(name="BillingMicroservice", url="http://localhost:8003/bills")
>>>>>>> main
public interface BillingFeignClient {

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") Date d1, @PathVariable("fechaFin") Date d2);

}