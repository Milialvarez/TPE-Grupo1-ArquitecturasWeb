package org.example.usermicroservice.services;

import org.example.usermicroservice.entities.Account;
import org.example.usermicroservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    public Account save(Account account){
        this.accountRepository.save(account);
        return account;
    }
    public void delete(Account account){

        accountRepository.delete(account);
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    public Account setAccountAnullated(Long id, boolean state) {
        Account ac = this.getAccountById(id);
        if (ac!=null) this.accountRepository.setAccountAnullated(id, state);
        return ac;

    }
}
