package org.example.monopatinmicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date fecha;

    @Column
    private Integer duracion;

    @Column
    private Long id_usuario;

    @OneToOne
    private Monopatin monopatin;
}
