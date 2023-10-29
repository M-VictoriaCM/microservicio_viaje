package com.microservicio_viaje.microservicio_viaje.repository;

import com.microservicio_viaje.microservicio_viaje.model.EstadoPausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPausaRepository extends JpaRepository<EstadoPausa, Integer> {
    /**
     * Busqueda por tipo de estado
     * @param nuevoEstado
     * @return resultado de la busqueda
     */
     @Query("SELECT e FROM EstadoPausa e WHERE e.estadoDeLaPausa = :nuevoEstado")
    EstadoPausa findByEstado(String nuevoEstado);

    /**
     * modifico un estado
     * @param estado
     * @param id
     */


}
