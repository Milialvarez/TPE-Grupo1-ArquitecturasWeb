package org.example.services;

import org.example.entities.Carrera;
import org.example.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CarerrasService")
public class CarrerasService implements BaseService<Carrera> {
    @Autowired
    private CarreraRepository carreraRepository;

    @Transactional
    @Override
    public List<Carrera> findAll() throws Exception {
        return null;
    }

    @Transactional
    @Override
    public Carrera findById(Long id) throws Exception {
        return null;
    }

    @Override
    public Carrera save(Carrera entity) throws Exception {
        return null;
    }

    @Transactional
    @Override
    public Carrera update(Long id, Carrera entity) throws Exception {
        return null;
    }

    @Transactional
    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
