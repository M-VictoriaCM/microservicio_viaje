package com.microservicio_viaje.microservicio_viaje.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Monopatin {
    private int idMonopatin;
    private int km_totales;
    private String ubicacion;
    private int km_recorridos;
    private Estado estado;
    private int cantViajes;
}
