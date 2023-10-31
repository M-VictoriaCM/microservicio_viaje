package com.microservicio_viaje.microservicio_viaje.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ViajeDTO {
    private int id;
    private String origenDelViaje;
    private String destinodDelViaje;
    private Time horaInicioViaje;
    private Time horaFinViaje;
    private Timestamp fechaDelViaje;
    private List<PausaDTO> pausas;
    private TarifaDTO tarifa;
}
