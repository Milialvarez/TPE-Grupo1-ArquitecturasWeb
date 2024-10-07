package org.example.controllers;

import org.example.entities.Alumno;
import org.example.services.AlumnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alumnos")
public class AlumnosController {
    @Autowired
    private AlumnosService service;

    @PostMapping
    public ResponseEntity<Alumno> save(Alumno a) throws Exception {
        try{
            this.service.save(a);
            return ResponseEntity.status(HttpStatus.CREATED).body(a);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(a);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception {
        try{
            List<Alumno> alumnos = this.service.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(alumnos);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. con id intente nuevamente despues.\"}");
        }
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
            boolean response = this.service.delete(id);
            if(response){
                return ResponseEntity.status(200).body("{\"error\":\"Se elimino correctamente el alumno con id\"" +id+ "\"}");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"error. No se pudo eliminar el alumno con id\"" +id+ "intente nuevamente despues.\"}");
    }


}
