package com.microservicio_viaje.microservicio_viaje.DTO;

import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
@Getter
@Setter
@NoArgsConstructor
public class PausaDTO {
    private int id;
    private Time horaInicioPausa;
    private Time horaFinPausa;
    private EstadoPausaDTO estado;
    private boolean finalizado;
    private ViajeDTO viaje;
}
