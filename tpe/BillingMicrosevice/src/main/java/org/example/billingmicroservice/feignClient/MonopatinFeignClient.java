package org.example.billingmicroservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(name="MonopatinMicroservice", url="http://localhost:8003/monopatin")
public interface MonopatinFeignClient {

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    ResponseEntity<?> getViajesBetween(@PathVariable("fechaOrigen") LocalDate d1, @PathVariable("fechaFin") LocalDate d2);

}
