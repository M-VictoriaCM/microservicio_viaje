
package com.microservicio_viaje.microservicio_viaje.repository;

import com.microservicio_viaje.microservicio_viaje.DTO.ViajeDTO;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
    //Hacer una consulta por año
    @Query("SELECT v FROM Viaje v WHERE YEAR(v.fechaDelViaje) = :año")
    List<Viaje> findAllViajesByYear(int año);

    @Query("SELECT v FROM Viaje v WHERE v.id = :id")
    Viaje findPorId(int id);
}
