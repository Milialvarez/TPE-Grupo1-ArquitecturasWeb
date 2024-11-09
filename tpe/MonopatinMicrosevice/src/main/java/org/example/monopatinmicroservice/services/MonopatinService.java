package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.repositories.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;

    public List<Monopatin> getAll() {
        return monopatinRepository.findAll();
    }

    public Monopatin add(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    public Monopatin getById(Long id) {
        return monopatinRepository.findById(id).orElse(null);
    }

    public List<Monopatin> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        return monopatinRepository.getMonopatinesPorViajesPorAnio(anio, xViajes);
    }

    public Monopatin delete(Long id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);

        if (monopatin != null) {
            monopatinRepository.deleteById(id);
        }

        return monopatin;
    }
}
