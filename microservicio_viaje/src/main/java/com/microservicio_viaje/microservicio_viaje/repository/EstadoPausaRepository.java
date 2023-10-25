package com.microservicio_viaje.microservicio_viaje.repository;

import com.microservicio_viaje.microservicio_viaje.model.EstadoPausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstadoPausaRepository extends JpaRepository<EstadoPausaRepository, Integer> {
    @Query("SELECT e FROM EstadoPasusa e where e.tipo = :nuevoEstado")
    public EstadoPausa findByEstado(String nuevoEstado);
}
