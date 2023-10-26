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
public class Viaje {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idViaje")
    private int id;
    @Column(nullable = false)
    private String origenDelViaje;
    @Column(nullable = false)
    private String destinoDelViaje;
    @Column(nullable = false)
    private Time horaInicioViaje;
    @Column(nullable = false)
    private Time horaFinViaje;
    @Column(nullable = false)
    private Timestamp fechaDelViaje;
    @Column(nullable = false)
    //Relacion con la tabla Pausa
    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pausa> pausas = new ArrayList<>();
    //Relacion con la tabla Tarifa
    @ManyToOne
    @JoinColumn(name = "tarifa_idTarifa")
    private Tarifa tarifa;



}
