package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.repositories.MonopatinRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepositorie monopatinRepositorie;

    public List<Monopatin> getAll() {
        return monopatinRepositorie.findAll();
    }

    public Monopatin add(Monopatin monopatin) {
        return monopatinRepositorie.save(monopatin);
    }

    public Monopatin getById(Long id) {
        Optional<Monopatin> result = monopatinRepositorie.findById(id);
        return result.orElse(null);
    }

    public List<Monopatin> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        return monopatinRepositorie.getMonopatinesPorViajesPorAnio(anio, xViajes);
    }
}
