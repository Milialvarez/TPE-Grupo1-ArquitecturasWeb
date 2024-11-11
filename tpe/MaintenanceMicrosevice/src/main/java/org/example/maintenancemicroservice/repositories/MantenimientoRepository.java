package org.example.maintenancemicroservice.repositories;

import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento,Long> {

    @Query("SELECT m FROM Mantenimiento m WHERE m.estado =:status")
    ArrayList<Mantenimiento> findAllByStatus(@PathVariable("status") String status);

}
