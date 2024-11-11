package org.example.maintenancemicroservice.controllers;

import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.services.MantenimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
