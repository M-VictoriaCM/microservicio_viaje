package com.microservicio_viaje.microservicio_viaje.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
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

public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idViaje")
    private int id;
    @Column(nullable = false)
    private String origenDelViaje;
    private String destinoDelViaje;
    @Column(nullable = false)
    private Time horaInicioViaje;
    private Time horaFinViaje;
    @Column(nullable = false)
    private Timestamp fechaDelViaje;
    private boolean isFinalizado;
    private double cobroViaje;
    private Monopatin monopatin;
    @Column(nullable = false)
    //Relacion con la tabla Pausa
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pausa> pausas = new ArrayList<>();
    //Relacion con la tabla Tarifa
    @Getter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "viaje_id")
    private List<Tarifa> tarifas = new ArrayList<>();

    public Viaje() {
    }

    public Viaje(Timestamp fechaDelViaje, String origenDelViaje, Time horaInicioViaje, boolean isFinalizado) {
        this.fechaDelViaje = fechaDelViaje;
        this.origenDelViaje = origenDelViaje;
        this.horaInicioViaje = horaInicioViaje;
        this.isFinalizado = isFinalizado;
    }

    public Viaje(String destinoDelViaje, Time horaFinViaje, Timestamp fechaDelViaje, boolean isFinalizado) {
        this.destinoDelViaje = destinoDelViaje;
        this.horaFinViaje = horaFinViaje;
        this.fechaDelViaje = fechaDelViaje;
        this.isFinalizado = isFinalizado;
    }

    public Viaje(String origenDelViaje, String destinoDelViaje, Time horaInicioViaje, Time horaFinViaje, Timestamp fechaDelViaje, boolean isFinalizado, double cobroViaje) {
        this.origenDelViaje = origenDelViaje;
        this.destinoDelViaje = destinoDelViaje;
        this.horaInicioViaje = horaInicioViaje;
        this.horaFinViaje = horaFinViaje;
        this.fechaDelViaje = fechaDelViaje;
        this.isFinalizado = isFinalizado;
        this.cobroViaje= cobroViaje;
        this.pausas = new ArrayList<>();
        this.tarifas = new ArrayList<>();
    }
    public void addPausaDelViaje(Pausa pausa){
        this.pausas.add(pausa);
    }

    public void addTarifaInicial(Tarifa tarifa){
        this.tarifas.add(tarifa);
    }
}
