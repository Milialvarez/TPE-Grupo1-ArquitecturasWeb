package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Parada;
import org.example.monopatinmicroservice.entities.Pausa;
import org.example.monopatinmicroservice.repositories.ParadaRepositorie;
import org.example.monopatinmicroservice.repositories.PausaRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PausaService {
    @Autowired
    private PausaRepositorie pausaRepositorie;

    public List<Pausa> getAll() {
        return pausaRepositorie.findAll();
    }

    public Pausa add(Pausa pausa) {
        return pausaRepositorie.save(pausa);
    }

    public Pausa getById(Long id) {
        return pausaRepositorie.findById(id).orElse(null);
    }

    public Pausa delete(Long id) {
        Pausa pausa = pausaRepositorie.findById(id).orElse(null);

        if (pausa != null) {
            pausaRepositorie.deleteById(id);
        }

        return pausa;
    }
}
