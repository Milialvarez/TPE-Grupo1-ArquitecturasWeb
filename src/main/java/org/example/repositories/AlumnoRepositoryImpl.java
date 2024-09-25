package main.java.org.example.repositories;

import main.java.org.example.criterios.Criterio;
import main.java.org.example.entities.Alumno;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class AlumnoRepositoryImpl implements AlumnoRepository {
    private EntityManager em;

    public AlumnoRepositoryImpl(EntityManager em) {
        this.em = em;
        em.getTransaction().begin();
    }

    @Override
    public Alumno getAlumnoById(Long id) {
        return em.find(Alumno.class, id);
    }

    @Override
    public List<Alumno> getAlumnos(String criterio, Boolean ascendente) {
        List<String> posiblesCriterios = new ArrayList<>();
        posiblesCriterios.add("nombre");
        posiblesCriterios.add("genero");
        posiblesCriterios.add("nro_libreta");
        posiblesCriterios.add("apellido");
        if(!posiblesCriterios.contains(criterio)) { throw new IllegalArgumentException("criterio invalido: "+criterio); }
        String orden;
        if(ascendente) {
            orden = "ASC";
        } else{
            orden = "DESC";
        }
        String select = "SELECT a FROM Alumno a ORDER BY "+ criterio +" "+orden;
        List<Alumno> lista = em.createQuery(select, Alumno.class).getResultList();
        return lista;
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

    @Override
    public Alumno getAlumnoByNroLibreta(int nroLibreta) {
        String select = "SELECT a FROM Alumno a WHERE a.nro_libreta = ?1";
        Query query = em.createQuery(select);
        query.setParameter(1, nroLibreta);
        try{
            return (Alumno) query.getSingleResult();
        }catch(NoResultException e){
            throw new IllegalArgumentException("Número de libreta no encontrada");
        }

    }

    @Override
    public List<Alumno> getAlumnosByGenero(String genero) {
        String select = "SELECT a FROM Alumno a WHERE a.genero like ?1";
        List<Alumno> alumnos = em.createQuery(select).setParameter(1, genero).getResultList();
        return alumnos;
    }
}
