package com.microservicio_viaje.microservicio_viaje.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Viaje {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idViaje")
    private int idViaje;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private Timestamp hora;

    @ManyToOne
    @JoinColumn(name = "tarifa_idTarifa")
    private Tarifa tarifa;

    @ManyToOne
    @JoinColumn(name = "pausa_idPausa")
    private Pausa pausa;
}
