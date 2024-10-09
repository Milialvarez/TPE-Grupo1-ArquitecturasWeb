package integrador.app.controllers;

import integrador.app.services.AlumnoCarreraService;
import integrador.app.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alumnos/carreras")
public class AlumnoCarreraController {
    @Autowired
    private AlumnoCarreraService alumnoCarreraService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
