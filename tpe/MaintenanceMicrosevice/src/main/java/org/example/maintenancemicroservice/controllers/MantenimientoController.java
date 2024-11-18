package org.example.maintenancemicroservice.controllers;

import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.models.Monopatin;
import org.example.maintenancemicroservice.services.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {
    @Autowired
    private MantenimientoService ms;

    @GetMapping
    public ResponseEntity<?> getMantenimientos() {
        try{
            ArrayList<Mantenimiento> m = this.ms.getAllManteinances();
            return ResponseEntity.ok(m);
        }catch(Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/km/{km}")
    public ResponseEntity<?> getMonopatinesReporteUsoKm(@PathVariable("km") float km) {
        try{
            ArrayList<Monopatin> lista = this.ms.getMonopatinesPorKm(km);
            return ResponseEntity.status(200).body(lista);
        }catch(Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/tiempo/{t}/{p}")
    public ResponseEntity<?> getMonopatinesPorTiempo(@PathVariable("t") float tiempo, @PathVariable(value = "p", required = false) boolean pausa) {
        try{
            ArrayList<Monopatin> lista = this.ms.getMonopatinesPorTiempo(tiempo, pausa);
            return ResponseEntity.status(200).body(lista);
        }catch(Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/estado/{status}")
    public ResponseEntity<?> getAllManteinanceByStatus(@PathVariable("status") String status) {
        try{
            ArrayList<Mantenimiento> manteinances = this.ms.getAll(status);
            return ResponseEntity.ok(manteinances);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    //el estado por defecto va a ser no disponible, se supone que si recién se lo está agregando es porque hay que arreglarlo
    @PostMapping("/{id_monopatin}")
    public ResponseEntity<?> saveManteinance(@PathVariable("id_monopatin") Long id_monopatin){
        try{
            Mantenimiento m = this.ms.save(id_monopatin);
            return ResponseEntity.ok(m);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("id/{id_monopatin}/estado/{status}") //Registrar fin de mantenimiento
    public ResponseEntity<?> updateStatus(@PathVariable("id_monopatin") int id, @PathVariable("status") String status){
        try{
            Integer idInteger = id;
            Long longId = idInteger.longValue();
            Mantenimiento mantenimiento = this.ms.updateMaintenance(longId, status);
            System.out.println(mantenimiento);
            return ResponseEntity.ok(mantenimiento);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_monopatin}")
    public ResponseEntity<?> getManteinanceByMonopatinId(@PathVariable("id_monopatin") int id_monopatin){
        try{
            Integer idInteger = id_monopatin;
            Long longId = idInteger.longValue();
            Mantenimiento mantenimiento = this.ms.findByMonopatinId(longId);
            if(mantenimiento != null) {
                return ResponseEntity.ok(mantenimiento);
            }else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "El monopatin con el id_monopatin " + longId + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }
        }catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
}
