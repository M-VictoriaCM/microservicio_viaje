package com.microservicio_viaje.microservicio_viaje.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EstadoPausa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstado")
    private int id;

    @Column(nullable = false)
    private String estadoDeLaPausa;
}
