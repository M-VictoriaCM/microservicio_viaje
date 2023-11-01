package com.microservicio_viaje.microservicio_viaje.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TarifaDTO {
    private String tipo;
    private int valor;
}
