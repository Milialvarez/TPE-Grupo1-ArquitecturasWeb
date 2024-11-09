package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository viajeRepository;

    public List<Viaje> getAll() {
        return viajeRepository.findAll();
    }

    public Viaje add(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    public Viaje getById(Long id) {
        return viajeRepository.findById(id).orElse(null);
    }

    public Viaje delete(Long id) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);

        if (viaje != null) {
            viajeRepository.deleteById(id);
        }

        return viaje;
    }
}
