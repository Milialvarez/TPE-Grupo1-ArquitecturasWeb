package integrador.app.repositories;

import integrador.app.entities.Alumno;
import integrador.app.entities.Alumno_Carrera;
import integrador.app.entities.Alumno_Carrera_Id;
import integrador.app.entities.Carrera;
import org.springframework.stereotype.Repository;

@Repository("AlumnoCarreraRepository")
public interface AlumnoCarreraRepository extends RepoBase<Alumno_Carrera, Long> {

}
