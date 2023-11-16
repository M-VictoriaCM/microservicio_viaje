package com.microservicio_viaje.microservicio_viaje.DTO;

import com.microservicio_viaje.microservicio_viaje.model.Monopatin;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class ViajeDTO {
    private String origenDelViaje;
    private String destinoDelViaje;
    private Time horaInicioViaje;
    private Time horaFinViaje;
    private Timestamp fechaDelViaje;
    private boolean isFinalizado;
    private double cobroViaje;


}
