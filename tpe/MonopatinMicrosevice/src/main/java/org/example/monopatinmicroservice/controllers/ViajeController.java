package org.example.monopatinmicroservice.controllers;

import org.example.monopatinmicroservice.dtos.ViajeConIdMonopatinDTO;
import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.services.MonopatinService;
import org.example.monopatinmicroservice.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/viaje")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    @Autowired
    private MonopatinService monopatinService;

    @GetMapping //Andando
    public ResponseEntity<?> getViajes() {
        try {
            List<Viaje> result = this.viajeService.getAll();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id_viaje}") //Andando
    public ResponseEntity<?> getViaje(@PathVariable("id_viaje") Long id) {
        try {
            Viaje result = this.viajeService.getById(id);

            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "El viaje con el id " + id + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addViaje(@RequestBody ViajeConIdMonopatinDTO viajeDTO) {
        try {
            Integer aux = viajeDTO.getId_monopatin();
            Long id = aux.longValue();
            Integer auxUs = viajeDTO.getId_usuario();
            Long idUs = auxUs.longValue();

            Monopatin monopatin = monopatinService.getById(id);

            Viaje viaje = new Viaje();
            viaje.setMonopatin(monopatin);
            viaje.setDuracion(viajeDTO.getDuracion());
            viaje.setKilometros(viajeDTO.getKilometros());
            viaje.setFecha(viajeDTO.getFecha());
            viaje.setId_usuario(idUs);

            Viaje result = this.viajeService.add(viaje);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id_viaje}")
    public ResponseEntity<?> deleteViaje(@PathVariable("id_viaje") Integer id) {
        try {
            Viaje result = this.viajeService.delete(id.longValue());

            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                HashMap<String, String> notFound = new HashMap<>();
                notFound.put("error", "El viaje con el id " + id + " no existe.");
                return ResponseEntity.status(404).body(notFound);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getViajesBetween(@PathVariable("fechaOrigen")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d1, @PathVariable("fechaFin")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d2){
        List<Viaje> result = this.viajeService.getViajesBetween(d1, d2);
        return ResponseEntity.ok().body(result);
    }
}
