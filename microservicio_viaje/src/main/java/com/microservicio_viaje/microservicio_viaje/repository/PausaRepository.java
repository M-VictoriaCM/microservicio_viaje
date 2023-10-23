package com.microservicio_viaje.microservicio_viaje.repository;

import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PausaRepository extends JpaRepository<Pausa, Integer> {
}
