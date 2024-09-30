package main.java.org.example.repositories;

import main.java.org.example.dtos.ReporteCarrerasDTO;
import main.java.org.example.entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.*;

public class CarreraRepositoryImpl implements CarreraRepository {
    private EntityManager em;

    public CarreraRepositoryImpl(EntityManager e){
        this.em=e;
    }

    @Override
    public void crearCarrera(Carrera c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }


    public Carrera buscarCarreraPorNombre(String c) {
        TypedQuery<Carrera> query = em.createQuery("SELECT c " +
                        "FROM Carrera c " +
                        "WHERE c.nombre = :carreraBuscada"
                , Carrera.class);
        query.setParameter("carreraBuscada", c);

        Carrera carrera = query.getSingleResult();

        return carrera;
    }

    @Override
    public List<Carrera> listarCarreras() {
        TypedQuery<Carrera> query = em.createQuery(
                "SELECT c " +
                        "FROM Carrera c", Carrera.class);

        List<Carrera> carreras = query.getResultList();

        return carreras;
    }

    public List<Carrera> listarCarrerasConAlumnosInscriptos() {
        TypedQuery<Carrera> query = em.createQuery(
                "SELECT c " +
                        "FROM Carrera c " +
                        "JOIN c.alumnos ac " +
                        "GROUP BY c " +
                        "HAVING COUNT(ac.alumno) > 0 " +
                        "ORDER BY COUNT(ac.alumno) DESC", Carrera.class);

        return query.getResultList();
    }

    public List<ReporteCarrerasDTO> getMajorsReport(){
        String querysql = "SELECT nombre AS carrera, anio, MAX(cant_inscriptos) AS cant_inscriptos, MAX(cant_graduados) AS cant_egresados " +
                "FROM ( " +
                "    SELECT c.nombre, YEAR(fechaGraduacion) as anio, COUNT(fechaGraduacion) AS cant_graduados, 0 AS cant_inscriptos " +
                "    FROM Carrera c LEFT JOIN Alumno_Carrera ac ON ac.id_carrera = c.carrera_id " +
                "    WHERE fechaGraduacion IS NOT NULL " +
                "    GROUP BY c.carrera_id, fechaGraduacion " +
                "    UNION " +
                "    SELECT c.nombre, YEAR(ac.fechaInscripcion), 0, COUNT(YEAR(ac.fechaInscripcion)) AS cant_inscriptos " +
                "    FROM Carrera c LEFT JOIN Alumno_Carrera ac ON ac.id_carrera = c.carrera_id " +
                "    GROUP BY c.carrera_id, YEAR(ac.fechaInscripcion)" +
                ") graduados_inscriptos " +
                "GROUP BY nombre, anio " +
                "ORDER BY nombre, anio;";

        Query query = em.createNativeQuery(querysql);
        List<Object[]> carreras = query.getResultList();

        List<ReporteCarrerasDTO> dtos = new ArrayList<>();
        for (Object[] fila:carreras){
            ReporteCarrerasDTO newDTO = new ReporteCarrerasDTO();
            // NOMBRE CARRERA
            newDTO.setNombreCarrera((String) fila[0]);

            // FECHA
            Date fecha = (Date) fila[1];
            if (fecha != null) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.setTime(fecha);
                int anio = calendar.get(java.util.Calendar.YEAR);
                newDTO.setAnio(anio);
            } else {
                newDTO.setAnio(0); // O el valor que desees asignar si la fecha es nula
            }

            //INSCRIPTOS
            java.math.BigInteger bigInteger = (BigInteger) fila[2];
            int entero = (Integer) bigInteger.intValue();
            newDTO.setInscriptos(entero);


            //GRADUADOS
            java.math.BigInteger bigInteger2 = (BigInteger) fila[3];
            int entero2 = (Integer) bigInteger2.intValue();
            newDTO.setEgresados(entero2);

            dtos.add(newDTO);
        }

        return dtos;
    }


}
