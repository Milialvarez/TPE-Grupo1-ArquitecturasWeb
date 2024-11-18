package org.example.adminmicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@FeignClient(name="BillingMicroservice", url="http://localhost:8007/bills")
public interface BillingFeignClient {
    @PostMapping
    ResponseEntity<?> setNewBill(Date fechaVigencia, float pFijo, float pExtra);

}