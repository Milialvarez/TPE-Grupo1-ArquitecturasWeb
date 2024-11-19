
package org.example.monopatinmicroservice.repositories;

import org.example.monopatinmicroservice.entities.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PausaRepository extends JpaRepository<Pausa, Long> {

    @Query("SELECT p FROM Pausa p WHERE p.viaje.id =:id")
    ArrayList<Pausa> getPausasByViajeId(@Param("id") Long id);
}
