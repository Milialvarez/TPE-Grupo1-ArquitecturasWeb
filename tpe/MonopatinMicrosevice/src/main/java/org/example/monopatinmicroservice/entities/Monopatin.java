package org.example.monopatinmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Monopatin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Float kmRecorridos;

    @Column
    private Float tiempoUso;

    @Column
    private Float tiempoUsoConPausas;

    @OneToOne
    @JoinColumn(nullable = true)
    @JsonManagedReference
    private Parada parada;

    @Column
    private int posX;
    @Column
    private int posY;
}
