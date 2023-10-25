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
    private Timestamp horaInicioViaje;
    @Column(nullable = false)
    private Timestamp horaFinViaje;
    @Column(nullable = false)
    private String estadoViaje;



    //Relacion de clase viaje con Pausa
    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pausa> pausas = new ArrayList<>();

    //Relacion de clase viaje con Tarifa
    @ManyToOne
    @JoinColumn(name = "tarifa_idTarifa")
    private Tarifa tarifa;



}
