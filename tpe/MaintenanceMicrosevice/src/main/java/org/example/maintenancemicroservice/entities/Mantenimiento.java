package org.example.maintenancemicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private Long id_monopatin;

    @Column(nullable = false)
    private String estado;

}
