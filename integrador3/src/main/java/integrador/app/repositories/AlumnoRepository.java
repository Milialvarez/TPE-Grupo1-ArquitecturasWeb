package integrador.app.repositories;

import integrador.app.entities.Alumno;
import org.springframework.stereotype.Repository;

@Repository("AlumnoRepository")
public interface AlumnoRepository extends RepoBase<Alumno, Long> {
}
