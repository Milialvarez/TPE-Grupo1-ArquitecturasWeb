package integrador.app.services;

import integrador.app.entities.Alumno;
import integrador.app.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Alumno> a = alumnoRepository.findById(id);
        return a.orElse(null);
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
        try {
            alumnoRepository.delete(findById(id));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public ArrayList<Alumno> getEstudiantesByGenero(String genero) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        alumnos = alumnoRepository.getEstudiantesByGenero(genero);
        return alumnos;
    }

    public Alumno getEstudianteByLibreta(int nroLibreta) {
        Alumno a = this.alumnoRepository.getEstudianteByLibreta(nroLibreta);
        return a;
    }

    public ArrayList<Alumno> getEstudiantesByCriterio(String criterio) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        alumnos = alumnoRepository.getEstudiantesByCriterio(criterio);
        return alumnos;
    }
}
