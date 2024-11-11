package org.example.maintenancemicroservice.services;

import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.repositories.MantenimientoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("MantenimientoService")
public class MantenimientoService {
    private MantenimientoRepository mantenimientoRepository;

    public ArrayList<Mantenimiento> getAll(String status) {
        return this.mantenimientoRepository.findAllByStatus(status);
    }

    public Mantenimiento save(Long idMonopatin) {
        Mantenimiento mantenimiento = new Mantenimiento(idMonopatin, "no disponible");
        return this.mantenimientoRepository.save(mantenimiento);
    }

    public Mantenimiento updateMaintenance(Long id, String status) {
        Mantenimiento mantenimiento = (Mantenimiento) this.mantenimientoRepository.findByIdMonopatin(id);
        if (mantenimiento != null) {
            this.mantenimientoRepository.update(id, status);
            return this.mantenimientoRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public Mantenimiento findByMonopatinId(Long idMonopatin) {
        Mantenimiento m = this.mantenimientoRepository.findByMonopatinId(idMonopatin);
        return m;
    }
}
