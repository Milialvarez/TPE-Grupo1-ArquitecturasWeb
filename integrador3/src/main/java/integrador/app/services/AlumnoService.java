package integrador.app.services;

import integrador.app.entities.Alumno;
import integrador.app.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AlumnoService")
public class AlumnoService implements BaseService<Alumno> {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Alumno> findAll() throws Exception {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno findById(Long id) throws Exception {
        return null;
    }

    @Override
    public Alumno save(Alumno entity) throws Exception {
        return this.alumnoRepository.save(entity);
    }

    @Override
    public Alumno update(Long id, Alumno entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
