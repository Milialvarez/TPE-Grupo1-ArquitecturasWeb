package integrador.app.repositories;

import integrador.app.entities.Alumno;
import integrador.app.entities.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CarreraRepository extends RepoBase<Carrera, Long> {

    @Query("SELECT c " +
            "FROM Carrera c " +
            "JOIN c.alumnos ac " +
            "GROUP BY c " +
            "HAVING COUNT(ac.alumno) > 0 " +
            "ORDER BY COUNT(ac.alumno) DESC")
    ArrayList<Carrera> getCarrerasConInscriptos();

    @Query("SELECT c FROM Carrera c WHERE c.nombre = :nombre")
    Carrera getMajorByName(@Param("nombre") String nombre);
}
