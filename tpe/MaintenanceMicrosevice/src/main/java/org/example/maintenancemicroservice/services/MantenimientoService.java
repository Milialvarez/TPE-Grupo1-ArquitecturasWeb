package org.example.maintenancemicroservice.services;

import jakarta.transaction.Transactional;
import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.feignClients.ReportsFeignClient;
import org.example.maintenancemicroservice.models.Monopatin;
import org.example.maintenancemicroservice.repositories.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("MantenimientoService")
public class MantenimientoService {
    @Autowired
    private MantenimientoRepository mantenimientoRepository;
    private ReportsFeignClient reportsFeignClient;

    public ArrayList<Mantenimiento> getAll(String status) {
        return this.mantenimientoRepository.findAllByStatus(status);
    }

    public ArrayList<Monopatin> getMonopatinesPorKm(float km){
        ResponseEntity<?> response = this.reportsFeignClient.getReporteUsoMonopatinKm(km);
        return (ArrayList<Monopatin>) response.getBody();
    }

    public ArrayList<Monopatin> getMonopatinesPorTiempo(float t, boolean p){
        ResponseEntity<?> response = this.reportsFeignClient.getReporteUsoMonopatinTiempoPausa(t, p);
        return (ArrayList<Monopatin>) response.getBody();
    }

    public Mantenimiento save(Long idMonopatin) {
        Mantenimiento mantenimiento = new Mantenimiento(idMonopatin, "no disponible");
        Mantenimiento result = this.mantenimientoRepository.save(mantenimiento);
        return result;
    }

    @Transactional
    public Mantenimiento updateMaintenance(Long id, String status) {
        Mantenimiento mantenimiento = this.mantenimientoRepository.findByIdMonopatin(id);
        if (mantenimiento != null) {
            this.mantenimientoRepository.update(id, status);
            mantenimiento.setEstado(status);
        }
        return mantenimiento;
    }

    public Mantenimiento findByMonopatinId(Long idMonopatin) {
        Mantenimiento m = this.mantenimientoRepository.findByMonopatinId(idMonopatin);
        return m;
    }

    public ArrayList<Mantenimiento> getAllManteinances() {
        ArrayList<Mantenimiento> m = (ArrayList<Mantenimiento>) this.mantenimientoRepository.findAll();
        return m;
    }
}
