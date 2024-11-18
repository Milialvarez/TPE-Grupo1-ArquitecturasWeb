package org.example.usermicroservice.controllers;

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
    public int anullateAccount(@PathVariable("id_acc") Long id_acc ){//Anular cuenta
        if(this.accountservice.getAccountById(id_acc) == null){
            return -1;
        } else{
            accountservice.setAccountAnullated(id_acc, true);
            return 1;
        }
    }


}
