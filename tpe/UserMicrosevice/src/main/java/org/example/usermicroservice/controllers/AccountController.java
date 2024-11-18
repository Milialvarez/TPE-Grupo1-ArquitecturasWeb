package org.example.usermicroservice.controllers;

import lombok.AllArgsConstructor;
import org.example.usermicroservice.entities.Account;
import org.example.usermicroservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountservice;

    @GetMapping //Andando
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountservice.getAll();
        if (accounts.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}") //Andando
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) {
        Account account = accountservice.getAccountById(id);
        if (account == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody Account account) {
        Account newAccount = this.accountservice.save(account);
        return ResponseEntity.ok(newAccount);
    }

    //modificar metodo para que sea desanulable :)
    @PutMapping("/null")
    public ResponseEntity<?> anullateAccount(@RequestBody Account acc){ //Anular cuenta
        Long id = acc.getId();
        if(accountservice.getAccountById(id) == null){
            return ResponseEntity.notFound().build();
        } else{
            accountservice.setAccountAnullated(id, true);
            return ResponseEntity.status(201).body("user anullated succesfully");
        }
    }


}
