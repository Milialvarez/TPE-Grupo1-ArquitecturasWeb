package org.example.repositories;

import org.example.entities.Alumno;
import org.example.entities.Carrera;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AlumnoRepository")
public interface AlumnoRepository extends RepoBase<Alumno, Long> {

    Alumno getAlumnoByNroLibreta(int nro_libreta);
    List<Alumno> getAlumnosByGenero(String genero);
    List<Alumno> getAlumnosByMajorFilteredBy(Carrera c, String city);
}