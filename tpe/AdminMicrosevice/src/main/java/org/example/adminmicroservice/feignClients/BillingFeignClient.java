package org.example.adminmicroservice.feignClients;

import org.example.adminmicroservice.models.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@FeignClient(name="BillingMicroservice", url="http://localhost:8007/bills")
public interface BillingFeignClient {
    @PostMapping
    ResponseEntity<?> setNewBill(@RequestBody Bill b);

}