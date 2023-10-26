package com.microservicio_viaje.microservicio_viaje.repository;

import com.microservicio_viaje.microservicio_viaje.model.EstadoPausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPausaRepository extends JpaRepository<EstadoPausa, Integer> {
    //Busco por estado
     @Query("SELECT e FROM EstadoPausa e WHERE e.estadoDeLaPausa = :nuevoEstado")
    EstadoPausa findByEstado(String nuevoEstado);


}
