package org.example.monopatinmicroservice.controllers;

import org.example.monopatinmicroservice.entities.Parada;
import org.example.monopatinmicroservice.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/paradas")
public class ParadaController {
    @Autowired
    private ParadaService paradaService;

    @GetMapping
    public ResponseEntity<?> getParadas() {
        try {
            List<Parada> result = this.paradaService.getAll();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id_parada}")
    public ResponseEntity<?> getParada(@PathVariable("id_parada") Long id) {
        try {
            Parada result = this.paradaService.getById(id);

            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "La parada con el id " + id + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addParada(@RequestBody Parada parada) { //Registrar parada
        try {
            Parada result = this.paradaService.add(parada);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id_parada}")
    public ResponseEntity<?> deleteParada(@PathVariable("id_parada") Long id) { //Quitar parada
        try {
            Parada result = this.paradaService.delete(id);

            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "La parada con el id " + id + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
