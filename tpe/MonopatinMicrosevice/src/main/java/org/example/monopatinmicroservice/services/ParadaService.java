package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Parada;
import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.repositories.ParadaRepositorie;
import org.example.monopatinmicroservice.repositories.ViajeRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService {
    @Autowired
    private ParadaRepositorie paradaRepositorie;

    public List<Parada> getAll() {
        return paradaRepositorie.findAll();
    }

    public Parada add(Parada parada) {
        return paradaRepositorie.save(parada);
    }

    public Parada getById(Long id) {
        return paradaRepositorie.findById(id).orElse(null);
    }

    public Parada delete(Long id) {
        Parada parada = paradaRepositorie.findById(id).orElse(null);

        if (parada != null) {
            paradaRepositorie.deleteById(id);
        }

        return parada;
    }
}
