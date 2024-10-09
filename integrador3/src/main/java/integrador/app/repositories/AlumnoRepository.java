package integrador.app.repositories;

import integrador.app.entities.Alumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("AlumnoRepository")
public interface AlumnoRepository extends RepoBase<Alumno, Long> {

    @Query("SELECT a FROM Alumno a WHERE a.genero like :genero")
    ArrayList<Alumno> getEstudiantesByGenero(@Param("genero") String genero);

    @Query("SELECT a FROM Alumno a WHERE a.nro_libreta = :nroLibreta")
    Alumno getEstudianteByLibreta(@Param("nroLibreta") int nroLibreta);

    @Query("SELECT a FROM Alumno a ORDER BY :criterio ASC")
    ArrayList<Alumno> getEstudiantesByCriterio(@Param("criterio") String criterio);
}