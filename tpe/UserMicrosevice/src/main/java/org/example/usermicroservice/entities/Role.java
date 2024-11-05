package org.example.billingmicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "role")  // La propiedad "rol" de la entidad Usuario
    private List<User> users;


}
