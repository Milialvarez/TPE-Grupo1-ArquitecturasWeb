package integrador.app.services;

import integrador.app.entities.Alumno_Carrera;
import integrador.app.entities.Carrera;
import integrador.app.repositories.AlumnoCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("AlumnoCarreraService")
public class AlumnoCarreraService implements BaseService<Alumno_Carrera> {
    @Autowired
    private AlumnoCarreraRepository alumnoCarreraRepository;
    @Autowired
    private CarreraService cs;

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
    public boolean delete(Long id) throws Exception {
        try {
            alumnoCarreraRepository.delete(findById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Object> getAlumnosByMajor(String ciudad, Long carrera_id) throws Exception {
        Carrera c = this.cs.findById(carrera_id);
        List<Object> l = new ArrayList<>();
        if (c != null) {
            l.addAll(this.alumnoCarreraRepository.getAlumnosByMajor(ciudad, carrera_id));
            return l;
        }
        throw new Exception("carrera con el id "+ carrera_id+" no encontrada");

    }
}
