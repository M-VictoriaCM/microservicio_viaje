package com.microservicio_viaje.microservicio_viaje.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tarifa {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTarifa")
    private int idTarifa;

    @Column(nullable = false)
    private int precioPorMinuto;

    @Column(nullable = false)
    private int tarifaExtraPorPausaExtensa;

    @OneToMany(mappedBy = "tarifa", fetch = FetchType.LAZY)
    private List<Viaje> viajes;

}
