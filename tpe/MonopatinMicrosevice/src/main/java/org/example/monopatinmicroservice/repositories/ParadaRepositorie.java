
package org.example.monopatinmicroservice.repositories;

import org.example.monopatinmicroservice.entities.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepositorie extends JpaRepository<Parada, Long> {
}
