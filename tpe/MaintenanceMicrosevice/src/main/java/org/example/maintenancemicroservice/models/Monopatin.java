package org.example.maintenancemicroservice.models;


import lombok.Getter;

public class Monopatin {
    @Getter
    private Long id;

    private Float kmRecorridos;

    private Parada parada;

    private int posX;
    private int posY;

}
