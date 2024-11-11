package org.example.maintenancemicroservice.services;

import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.repositories.MantenimientoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("MantenimientoService")
public class MantenimientoService {
    private MantenimientoRepository mantenimientoRepository;

    public ArrayList<Mantenimiento> getAll(String status){
        return this.mantenimientoRepository.findAllByStatus(status);
    }
}
