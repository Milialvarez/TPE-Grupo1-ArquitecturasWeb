package org.example.controllers;

import org.example.services.CarrerasService;

public class CarrerasController {
    private CarrerasService carrerasService;

    public CarrerasController(CarrerasService carrerasService) {
        this.carrerasService = carrerasService;
    }
}
