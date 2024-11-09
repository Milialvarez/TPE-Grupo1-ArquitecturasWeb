package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Pausa;
import org.example.monopatinmicroservice.repositories.PausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PausaService {
    @Autowired
    private PausaRepository pausaRepository;

    public List<Pausa> getAll() {
        return pausaRepository.findAll();
    }

    public Pausa add(Pausa pausa) {
        return pausaRepository.save(pausa);
    }

    public Pausa getById(Long id) {
        return pausaRepository.findById(id).orElse(null);
    }

    public Pausa delete(Long id) {
        Pausa pausa = pausaRepository.findById(id).orElse(null);

        if (pausa != null) {
            pausaRepository.deleteById(id);
        }

        return pausa;
    }
}
