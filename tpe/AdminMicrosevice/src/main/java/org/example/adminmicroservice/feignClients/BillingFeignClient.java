package org.example.adminmicroservice.feignClients;

import org.example.adminmicroservice.dtos.BillDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="BillingMicroservice", url="http://localhost:8007/bills")
public interface BillingFeignClient {
    @PostMapping
    ResponseEntity<?> setNewBill(@RequestBody BillDTO b);

}