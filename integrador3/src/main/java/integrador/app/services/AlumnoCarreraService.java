package integrador.app.services;

import integrador.app.entities.Alumno;
import integrador.app.entities.Alumno_Carrera;
import integrador.app.repositories.AlumnoCarreraRepository;
import integrador.app.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AlumnoCarreraService")
public class AlumnoCarreraService implements BaseService<Alumno_Carrera> {
    @Autowired
    private AlumnoCarreraRepository alumnoCarreraRepository;

    @Override
    public List<Alumno_Carrera> findAll() throws Exception {
        return alumnoCarreraRepository.findAll();
    }

    @Override
    public List<Alumno_Carrera> findAll(Sort sort) throws Exception {
        return null;
    }

    @Override
    public Alumno_Carrera findById(Long id) throws Exception {
        return null;
    }

    @Override
    public Alumno_Carrera save(Alumno_Carrera entity) throws Exception {
        return this.alumnoCarreraRepository.save(entity);
    }

    @Override
    public Alumno_Carrera update(Long id, Alumno_Carrera entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
