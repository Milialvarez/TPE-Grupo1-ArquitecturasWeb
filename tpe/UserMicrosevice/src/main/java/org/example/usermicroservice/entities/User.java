package org.example.billingmicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @ManyToMany
    private List<Account> accounts;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Role role;


}
