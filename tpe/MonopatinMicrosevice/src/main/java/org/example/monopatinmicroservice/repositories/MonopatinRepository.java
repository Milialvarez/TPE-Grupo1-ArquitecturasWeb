package org.example.monopatinmicroservice.repositories;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("SELECT m FROM Monopatin m JOIN Viaje v ON v.monopatin.id = m.id  WHERE YEAR(v.fecha) = :anio GROUP BY m HAVING COUNT(m) >= :xViajes")
    List<Monopatin> getMonopatinesPorViajesPorAnio(@Param("anio") Integer anio, @Param("xViajes") Integer xViajes);
}
