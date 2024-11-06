package org.example.adminmicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
//    private List<Account> accounts;
//    private Role role;
}
