package com.microservicio_viaje.microservicio_viaje.repository;

import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PausaRepository extends JpaRepository<Pausa, Integer> {
    @Query("SELECT p FROM Pausa p WHERE p.viaje.id = :idViaje")
    List<Pausa> findPausaByViaje(@Param("idViaje") int idViaje);
}
