package main.java.org.example.repositories;

import main.java.org.example.criterios.Criterio;
import main.java.org.example.entities.Alumno;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AlumnoRepositoryImpl implements AlumnoRepository {
    private EntityManager em;

    public AlumnoRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Alumno getAlumnoById(Long id) {
        return em.find(Alumno.class, id);
    }

    @Override
    public Alumno getAlumnos(Criterio seleccion) {

        return null;
    }

    @Override
    public Alumno saveAlumno(Alumno a) {
        if (a.getAlumno_id() == null) {
            em.persist(a);
        } else {
            a = em.merge(a);
        }
        return a;
    }

    @Override
    public Alumno deleteAlumno(Long id) {
        Alumno a = this.getAlumnoById(id);
        if (a != null) {
            em.remove(a);
        }
        return a;
    }

    @Override
    public Alumno updateAlumno(Alumno a, Long id) {
        String nombre = a.getNombre();
        String apellido = a.getApellido();
        int edad = a.getEdad();
        String genero = a.getGenero();
        String ciudad = a.getCiudad();
        int nro = a.getNro_libreta();
        int dni = a.getDni();
        String update = "UPDATE Alumno SET nombre = ?1, apellido = ?2, edad = ?3, genero = ?4, dni = ?5, nro_libreta = ?6, ciudad = ?7 WHERE alumno_id = ?8";
        Query q = em.createQuery(update);
        q.setParameter(1, nombre);
        q.setParameter(2, apellido);
        q.setParameter(3, edad);
        q.setParameter(4, genero);
        q.setParameter(5, dni);
        q.setParameter(6, nro);
        q.setParameter(7, ciudad);
        q.setParameter(8, id);
        q.executeUpdate();

        return this.getAlumnoById(id);
    }
}
