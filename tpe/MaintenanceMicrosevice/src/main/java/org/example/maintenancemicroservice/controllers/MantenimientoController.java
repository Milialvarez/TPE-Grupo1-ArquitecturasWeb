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
//            return ResponseEntity.notFound().build();
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

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

    @PutMapping("id/{id}/estado/{status}") //Registrar fin de mantenimiento
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id, @PathVariable String status){
        try{
            Mantenimiento mantenimiento = this.ms.updateMaintenance(id, status);
            System.out.println(mantenimiento);
            return ResponseEntity.ok(mantenimiento);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_monopatin}")
    public ResponseEntity<?> getManteinanceByMonopatinId(@PathVariable Long id_monopatin){
        try{
            Mantenimiento mantenimiento = this.ms.findByMonopatinId(id_monopatin);
            if(mantenimiento != null) {
                return ResponseEntity.ok(mantenimiento);
            }else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "El monopatin con el id " + id_monopatin + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }
        }catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
}
