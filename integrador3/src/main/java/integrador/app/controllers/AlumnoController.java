package integrador.app.controllers;

import integrador.app.entities.Alumno;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import integrador.app.services.AlumnoService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Alumno alumno) {
        try{
            Alumno a = this.alumnoService.save(alumno);

            return ResponseEntity.status(HttpStatus.CREATED).body(a);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        try{
            Alumno a = this.alumnoService.findById(id);

            if (a == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Alumno no encontrado");

                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(a);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try{
            boolean deleted = this.alumnoService.delete(id);

            if (deleted) {
                Map<String, String> response = new HashMap<>();
                response.put("msg", "Alumno con id " + id + " eliminado.");

                return ResponseEntity.status(HttpStatus.OK).body(response);

            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Alumno no encontrado");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
