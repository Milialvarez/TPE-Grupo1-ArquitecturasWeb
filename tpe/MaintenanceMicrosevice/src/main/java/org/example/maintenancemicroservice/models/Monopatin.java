package org.example.maintenancemicroservice.models;


import jakarta.persistence.Column;
import lombok.Getter;

public class Monopatin {
    @Getter
    private Long id;

    private Float kmRecorridos;
    private Float tiempoUso;
    private Float tiempoUsoConPausas;

    private Parada parada;

    private int posX;
    private int posY;

}
