package main.java.org.example.repositories;

import main.java.org.example.entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        "WHERE EXISTS ( " +
                        "    SELECT ac.carrera " +
                        "    FROM Alumno_Carrera ac " +
                        "    WHERE ac.carrera = c " +
                        ") " +
                        "ORDER BY ( " +
                        "    SELECT COUNT(ac.alumno) " +
                        "    FROM Alumno_Carrera ac " +
                        "    WHERE ac.carrera = c " +
                        ") DESC", Carrera.class);

        return query.getResultList();
    }

    public List<Carrera> getMajorsReport() {

        TypedQuery<Object[]> query = em.createQuery(
                "SELECT c, ac.antiguedad, " +
                        "COUNT(ac.alumno) AS inscriptos, " +
                        "SUM(CASE WHEN ac.graduado = true THEN 1 ELSE 0 END) AS egresados " +
                        "FROM Carrera c " +
                        "JOIN c.alumnos ac " +
                        "GROUP BY c, ac.antiguedad " +
                        "ORDER BY c.nombre ASC, ac.antiguedad ASC", Object[].class);

        // Obtener los resultados
        List<Object[]> results = query.getResultList();

        // Crear un Map para almacenar las carreras y su información asociada
        Map<Carrera, Map<Integer, int[]>> carreraReporte = new HashMap<>();

        // Procesar los resultados y agrupar por carrera
        for (Object[] row : results) {
            Carrera carrera = (Carrera) row[0]; // Carrera
            int antiguedad = (int) row[1];      // Año de inscripción
            long inscriptos = (long) row[2];    // Cantidad de inscriptos
            long egresados = (long) row[3];     // Cantidad de egresados

            // Obtener o crear el Map para la carrera
            Map<Integer, int[]> yearReport = carreraReporte.getOrDefault(carrera, new HashMap<>());
            yearReport.put(antiguedad, new int[]{(int) inscriptos, (int) egresados});
            carreraReporte.put(carrera, yearReport);
        }

        // Crear una lista final de carreras con la información de inscriptos y egresados
        List<Carrera> carreras = new ArrayList<>(carreraReporte.keySet());

        // Aquí puedes hacer más procesamiento si deseas agregar la información agregada al modelo Carrera

        return carreras;
    }

}
