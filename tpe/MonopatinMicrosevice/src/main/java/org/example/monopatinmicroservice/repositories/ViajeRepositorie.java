package org.example.monopatinmicroservice.repositories;

import org.example.monopatinmicroservice.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepositorie extends JpaRepository<Viaje, Long> {
}
