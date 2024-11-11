package org.example.monopatinmicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="MaintenanceMicroservice", url="http://localhost:8004/mantenimiento")
public interface ManteinanceFeignClient {

    @GetMapping("/unvailable")
    ResponseEntity<?> getAllManteinanceUnvailable();

    @GetMapping("/active")
    ResponseEntity<?> getAllManteinanceActive();
}
