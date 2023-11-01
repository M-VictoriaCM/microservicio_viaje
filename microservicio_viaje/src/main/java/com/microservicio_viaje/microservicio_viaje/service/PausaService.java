package com.microservicio_viaje.microservicio_viaje.service;

import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.PausaRepository;
import com.microservicio_viaje.microservicio_viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class PausaService {
    @Autowired
    private  final PausaRepository pausaRepository;
    @Autowired
    private final ViajeRepository viajeRepository;

    public PausaService(PausaRepository pausaRepository, ViajeRepository viajeRepository) {
        this.pausaRepository = pausaRepository;
        this.viajeRepository = viajeRepository;
    }

    public List<Pausa> getAllPausa() {
        return pausaRepository.findAll();
    }

    public Pausa getId(int id) {
        return pausaRepository.findById(id).orElse(null);
    }


    public Pausa crearPausa(int id) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);
        Pausa pausa = new Pausa();
        pausa.setHoraInicioPausa(new Time(System.currentTimeMillis()));
        pausa.setEstadoDeLaPausa("pausado");
        pausa.setViaje(viaje);
        viaje.addPausaDelViaje(pausa);
        //guarda la pausa en la base de datos
        pausaRepository.save(pausa);
        viajeRepository.save(viaje);
        return pausa;
    }

    public Pausa cancelarPausa(int pausaId) {
        Pausa pausa = pausaRepository.findById(pausaId).orElse(null);
        if (pausa != null) {
            pausa.setEstadoDeLaPausa("cancelada");
            pausa.setHoraFinPausa(new Time(System.currentTimeMillis()));
            pausaRepository.save(pausa);
            return pausa;
        }
        return null;
    }


}

