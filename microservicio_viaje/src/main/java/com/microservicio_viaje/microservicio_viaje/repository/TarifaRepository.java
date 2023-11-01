package com.microservicio_viaje.microservicio_viaje.repository;

import com.microservicio_viaje.microservicio_viaje.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {
    // Devuelve el precio por minuto
    // En tu repositorio de tarifas
    @Query("SELECT t FROM Tarifa t WHERE t.tipo = :tipo")
    Tarifa findPrecioPorMinuto(String tipo);

    /**@Query("SELECT t.tarifaExtraPorPausaExtensa FROM Tarifa t")
    Tarifa findByPrecioExtra();**/
}
