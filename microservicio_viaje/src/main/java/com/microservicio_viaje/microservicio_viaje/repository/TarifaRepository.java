package com.microservicio_viaje.microservicio_viaje.repository;

import com.microservicio_viaje.microservicio_viaje.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {

}
