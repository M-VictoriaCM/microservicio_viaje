
package com.microservicio_viaje.microservicio_viaje.repository;

import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
    //Hacer una consulta por año
    @Query("SELECT v FROM Viaje v WHERE YEAR(v.fechaDelViaje) = :anio")
    List<Viaje> findAllViajesByYear(int anio);

    @Query("SELECT v FROM Viaje v WHERE v.id = :id")
    Viaje findPorId(int id);

    //Viajes con pausas
    @Query("SELECT v FROM Viaje v JOIN v.pausas p WHERE p.estadoDeLaPausa = 'cancelada'")
    List<Viaje> findViajesConPausa();

    @Query("SELECT v FROM Viaje v WHERE YEAR(v.fechaDelViaje) = :anio AND MONTH(v.fechaDelViaje) BETWEEN :mesInicio AND :mesFin")
    List<Viaje> finByRangoDeMes(int anio, int mesInicio, int mesFin);
}
