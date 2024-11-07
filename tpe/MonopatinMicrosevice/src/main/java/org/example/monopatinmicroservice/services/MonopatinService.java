package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.repositories.MonopatinRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return monopatinRepositorie.findById(id).orElse(null);
    }

    public List<Monopatin> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        return monopatinRepositorie.getMonopatinesPorViajesPorAnio(anio, xViajes);
    }

    public Monopatin delete(Long id) {
        Monopatin monopatin = monopatinRepositorie.findById(id).orElse(null);

        if (monopatin != null) {
            monopatinRepositorie.deleteById(id);
        }

        return monopatin;
    }
}
