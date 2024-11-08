
package org.example.monopatinmicroservice.repositories;

import org.example.monopatinmicroservice.entities.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PausaRepositorie extends JpaRepository<Pausa, Long> {
}
