package main.java.org.example.repositories;

import main.java.org.example.criterios.Criterio;
import main.java.org.example.entities.Alumno;

public interface AlumnoRepository {
    Alumno getAlumnoById(Long id);
    Alumno getAlumnos(Criterio seleccion);
    Alumno saveAlumno(Alumno a);
    Alumno deleteAlumno(Long id);
    Alumno updateAlumno(Alumno a, Long id);
}
