package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.repositories.ViajeRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepositorie viajeRepositorie;

    public List<Viaje> getAll() {
        return viajeRepositorie.findAll();
    }

    public Viaje add(Viaje viaje) {
        return viajeRepositorie.save(viaje);
    }

    public Viaje getById(Long id) {
        return viajeRepositorie.findById(id).orElse(null);
    }

    public Viaje delete(Long id) {
        Viaje viaje = viajeRepositorie.findById(id).orElse(null);

        if (viaje != null) {
            viajeRepositorie.deleteById(id);
        }

        return viaje;
    }
}
