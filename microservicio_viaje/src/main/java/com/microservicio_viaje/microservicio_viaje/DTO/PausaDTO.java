package com.microservicio_viaje.microservicio_viaje.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
@Setter
@Getter
@NoArgsConstructor
public class PausaDTO {
    private Time horaInicioPausa;
    private Time horaFinPausa;
    private String estadoDeLaPausa;
    private ViajeDTO viajeDTO;
}
