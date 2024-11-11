package org.example.maintenancemicroservice.controllers;

import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.models.Monopatin;
import org.example.maintenancemicroservice.services.MantenimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {
    private MantenimientoService ms;

    @GetMapping("/unvailable")
    public ResponseEntity<?> getAllManteinanceUnvailable(){
        try{
            ArrayList<Mantenimiento> manteinances = this.ms.getAll("no disponible");
            return ResponseEntity.ok(manteinances);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getAllManteinanceActive(){
        try{
            ArrayList<Mantenimiento> manteinances = this.ms.getAll("activo");
            return ResponseEntity.ok(manteinances);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    //el estado por defecto va a ser no disponible, se supone que si recién se lo está agregando es porque hay que arreglarlo
    @PostMapping("/{id_monopatin}")
    public ResponseEntity<?> saveManteinance(@PathVariable Long id_monopatin){
        try{
            Mantenimiento m = this.ms.save(id_monopatin);
            return ResponseEntity.ok(m);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/estado/{status}")
    public ResponseEntity<?> updateStatus(@RequestBody Monopatin m, @PathVariable String status){
        try{
            Mantenimiento mantenimiento = this.ms.updateMaintenance(m.getId(), status);
            return ResponseEntity.ok(m);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }
}
