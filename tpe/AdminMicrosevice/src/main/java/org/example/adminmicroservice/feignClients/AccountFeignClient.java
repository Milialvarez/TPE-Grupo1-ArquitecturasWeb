package org.example.adminmicroservice.feignClients;

import org.example.adminmicroservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="AccountsMicroservice", url="http://localhost:8001/accounts")
public interface AccountsFeignClient {

    @GetMapping
    ResponseEntity<List<Account>> getAllAccounts();

    @GetMapping("/{id}")
    ResponseEntity<Account> getAccountById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<Account> save(@RequestBody Account acc);

    @PutMapping("/null")
    ResponseEntity<?> anullateAccount(@RequestBody Account acc);
}
