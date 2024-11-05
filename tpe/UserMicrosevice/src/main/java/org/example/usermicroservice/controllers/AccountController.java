package org.example.billingmicroservice.controllers;

import org.example.billingmicroservice.entities.Account;
import org.example.billingmicroservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/accounts")
public class AccountController {
    @Autowired
    AccountService accountservice;

    @GetMapping("/")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountservice.getAll();
        if (accounts.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) {
        Account account = accountservice.getAccountById(id);
        if (account == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping("/")
    public ResponseEntity<Account> save(@RequestBody Account account) {
        Account newAccount = accountservice.save(account);
        return ResponseEntity.ok(newAccount);
    }


}
