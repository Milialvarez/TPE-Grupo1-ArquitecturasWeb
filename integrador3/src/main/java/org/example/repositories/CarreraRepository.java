package org.example.repositories;

import org.example.dtos.ReporteCarrerasDTO;
import org.example.entities.Carrera;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CarerraRepository")
public interface CarreraRepository extends RepoBase<Carrera, Long> {

     Carrera buscarCarreraPorNombre(String c);
     List<Carrera> listarCarrerasConAlumnosInscriptos();
     List<ReporteCarrerasDTO> getMajorsReport();
}
