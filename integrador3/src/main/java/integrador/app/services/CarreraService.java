package integrador.app.services;

import integrador.app.entities.Alumno;
import integrador.app.entities.Carrera;
import integrador.app.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("CarreraService")
public class CarreraService implements BaseService<Carrera> {
    @Autowired
    private CarreraRepository carreraRepository;
    public CarreraService() {

    }

    @Override
    public List<Carrera> findAll() throws Exception {
        return carreraRepository.findAll();
    }

    public List<Carrera> findAll(Sort s) throws Exception {
        return carreraRepository.findAll(s);
    }

    @Override
    public Carrera findById(Long id) throws Exception {
        Optional<Carrera> c = carreraRepository.findById(id);
        return c.orElse(null);
    }

    @Override
    public Carrera save(Carrera entity) throws Exception {
        return carreraRepository.save(entity);
    }

    @Override
    public Carrera update(Long id, Carrera entity) throws Exception {
        return null;
    }


    @Override
    public boolean delete(Long id) throws Exception {
        try {
            carreraRepository.delete(findById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Carrera> getCarrerasConInscriptos() {
        return this.carreraRepository.getCarrerasConInscriptos();
    }
}
