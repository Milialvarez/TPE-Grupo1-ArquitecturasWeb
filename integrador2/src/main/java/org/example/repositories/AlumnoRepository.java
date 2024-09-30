package main.java.org.example.repositories;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Carrera;

import java.util.List;

public interface AlumnoRepository {
    Alumno getAlumnoById(Long id);
    List<Alumno> getAlumnos(String criterio, Boolean orden);
    //Alumno saveAlumno(Alumno a);
    Alumno deleteAlumno(Long id);
    Alumno updateAlumno(Alumno a, Long id);
    Alumno getAlumnoByNroLibreta(int nroLibreta);
    List<Alumno> getAlumnosByGenero(String genero);
    List<Alumno> getAlumnosByMajorFilteredBy(Carrera c, String city);
}
