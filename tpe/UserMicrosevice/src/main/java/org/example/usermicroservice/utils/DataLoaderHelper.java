package org.example.usermicroservice.utils;

import org.example.usermicroservice.entities.Account;
import org.example.usermicroservice.entities.Role;
import org.example.usermicroservice.entities.User;
import org.example.usermicroservice.repositories.AccountRepository;
import org.example.usermicroservice.repositories.RoleRepository;
import org.example.usermicroservice.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class DataLoaderHelper {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataLoaderHelper(AccountRepository accountRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void loadAccounts(String filePath) throws ParseException {
        List<String[]> records = CSVReaderHelper.readCSV(filePath);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (String[] record : records.subList(1, records.size())) {
            Account account = new Account();
            account.setId(Long.parseLong(record[0]));
            account.setCreation_date(dateFormat.parse(record[1]));
            account.setBalance(Integer.parseInt(record[2]));
            account.setAnullated(Boolean.parseBoolean(record[3]));
            accountRepository.save(account);
        }
    }

    @Transactional
    public void loadRoles(String filePath) {
        List<String[]> records = CSVReaderHelper.readCSV(filePath);

        for (String[] record : records.subList(1, records.size())) {
            Role role = new Role();
            role.setId(Long.parseLong(record[0]));
            role.setRole(record[1]);
            roleRepository.save(role);
        }
    }

    @Transactional
    public void loadUsers(String filePath) {
        List<String[]> records = CSVReaderHelper.readCSV(filePath);

        for (String[] record : records.subList(1, records.size())) {
            User user = new User();
            user.setId(Long.parseLong(record[0]));
            user.setName(record[1]);
            user.setPhoneNumber(record[2]);
            user.setEmail(record[3]);

            // Set role
            Role role = roleRepository.findById(Long.parseLong(record[4])).orElseThrow();
            user.setRole(role);

            userRepository.save(user);
        }
    }

    @Transactional
    public void loadUserAccounts(String filePath) {
        List<String[]> records = CSVReaderHelper.readCSV(filePath);

        for (String[] record : records.subList(1, records.size())) {
            User user = userRepository.findById(Long.parseLong(record[0])).orElseThrow();
            Account account = accountRepository.findById(Long.parseLong(record[1])).orElseThrow();

            user.getAccounts().add(account);
            account.getUsers().add(user);

            userRepository.save(user);
            accountRepository.save(account);
        }
    }
}

