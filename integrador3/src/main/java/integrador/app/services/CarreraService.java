package integrador.app.services;

import integrador.app.entities.Carrera;
import integrador.app.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Carrera findById(Long id) throws Exception {
        return carreraRepository.findById(id).get();
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
