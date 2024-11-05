package org.example.billingmicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Date creation_date;

    @Column
    private Integer balance;

    @ManyToMany(mappedBy = "accounts") // Especificamos el lado inverso de la relación
    private List<User> users;


}
