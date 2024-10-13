package integrador.app.controllers;

import integrador.app.entities.Alumno;
import integrador.app.entities.Alumno_Carrera;
import integrador.app.entities.Carrera;
import integrador.app.repositories.AlumnoCarreraRepository;
import integrador.app.repositories.AlumnoRepository;
import integrador.app.repositories.CarreraRepository;
import integrador.app.services.AlumnoCarreraService;
import integrador.app.services.AlumnoService;
import integrador.app.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/alumnos/carreras")
public class AlumnoCarreraController {
    @Autowired
    private AlumnoCarreraService alumnoCarreraService;
    @Autowired
    private AlumnoService as;
    @Autowired
    private CarreraService cs;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoCarreraService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/populate")
    public ResponseEntity<?> populate() {
        try{
            for (Alumno al : as.findAll()){
                Alumno_Carrera ac = new Alumno_Carrera(al, getRandomMajor(5, cs.findAll()), getRandomYear(false), getRandomYear(true), 3L);
                alumnoCarreraService.save(ac);
            }

            Map<String, String> response = new HashMap<>();
            response.put("msg", "Tabla carreras cargada");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static Carrera getRandomMajor(int n, List<Carrera> carreras){
        Random r = new Random();
        return carreras.get((r.nextInt(n)));
    }

    public Long getRandomYear(boolean puedeCero) {
        Long[] years;

        if (puedeCero) {
            years = new Long[]{0L, 2025L, 2026L, 2027L};
        } else {
            years = new Long[]{2021L, 2022L, 2023L, 2024L};
        }

        int numero = (int) (Math.random() * years.length);
        return years[numero];
    }
}
