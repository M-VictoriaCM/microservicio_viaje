package com.microservicio_viaje.microservicio_viaje.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pausa {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPausa")
    private int id;
    @Column(nullable = false)
    private Time horaInicioPausa;
    @Column(nullable = false)
    private Time horaFinPausa;

    //Relaciones entre la clase Pausa y la clase Viaje
    @ManyToOne
    @JoinColumn(name = "viaje_idViaje")
    private Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "estado_pausa_id")
    private EstadoPausa estado;

}
