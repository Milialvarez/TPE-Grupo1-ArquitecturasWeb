package org.example.monopatinmicroservice.controllers;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.services.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {
    @Autowired
    private MonopatinService monopatinService;

    @GetMapping
    public ResponseEntity<?> getMonopatines() {
        try {
            List<Monopatin> result = this.monopatinService.getAll();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id_monopatin}")
    public ResponseEntity<?> getMonopatin(@PathVariable("id_monopatin") Long id) {
        try {
            Monopatin result = this.monopatinService.getById(id);

            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "El monopatin con el id " + id + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addMonopatine(@RequestBody Monopatin monopatin) {
        try {
            Monopatin result = this.monopatinService.add(monopatin);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/xViajes/{xViajes}/anio/{anio}")
    public ResponseEntity<?> getMonopatinesPorViajesPorAnio(@PathVariable("anio") Integer anio, @PathVariable("xViajes") Integer xViajes) {
        try {
            List<Monopatin> result = this.monopatinService.getMonopatinesPorViajesPorAnio(anio, xViajes);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
