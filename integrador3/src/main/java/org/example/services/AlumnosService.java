package org.example.services;

import org.example.entities.Alumno;
import org.example.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AlumnosService")
public class AlumnosService implements BaseService<Alumno> {
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Transactional
    @Override
    public List<Alumno> findAll() throws Exception {
        return alumnoRepository.findAll();
    }
    @Transactional
    @Override
    public Alumno findById(Long id) throws Exception {
        return null;
    }
    @Transactional
    @Override
    public Alumno save(Alumno entity) throws Exception {
        return null;
    }
    @Transactional
    @Override
    public Alumno update(Long id, Alumno entity) throws Exception {
        return null;
    }
    @Transactional
    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }

}
