package org.example.usermicroservice.controllers;

import org.example.usermicroservice.config.RestTemplateConfig;
import org.example.usermicroservice.entities.Account;
import org.example.usermicroservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountservice;
    private RestTemplateConfig restTemplateConfig;

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
        Account newAccount = accountservice.save(account);
        return ResponseEntity.ok(newAccount);
    }

    //modificar metodo para que sea desanulable :)
    @PutMapping("/null/{id_acc}")
    public ResponseEntity<?> anullateAccount(@PathVariable("id_acc") int id_acc ){//Anular cuenta
        Integer aux = id_acc;
        Long longId = aux.longValue();
        if(this.accountservice.getAccountById(longId) == null){
            return ResponseEntity.notFound().build();
        } else{
            System.out.println("entré al else de account controller");
            Account a = accountservice.setAccountAnullated(longId, true);
            return ResponseEntity.status(201).body(a);
        }
    }

}
