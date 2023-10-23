package com.microservicio_viaje.microservicio_viaje.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int idPausa;

    @Column(nullable = false)
    private Timestamp hora_inicio;

    @Column(nullable = false)
    private Timestamp hora_fin;

    @ManyToOne // Corrección en la relación
    @JoinColumn(name = "viaje_idViaje") // Cambia el nombre de la columna a la que se hace referencia
    private Viaje viaje;
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPausa")
    private int idPausa;
    @Column(nullable = false)
    private Timestamp hora_inicio;
    @Column(nullable = false)
    private Timestamp hora_fin;

    @OneToMany(mappedBy = "viaje", fetch = FetchType.LAZY)
    private List<Pausa> pausas;*/

}
