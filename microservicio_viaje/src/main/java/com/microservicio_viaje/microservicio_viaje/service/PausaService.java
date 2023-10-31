package com.microservicio_viaje.microservicio_viaje.service;

import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.PausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;

@Service
public class PausaService {
   private final PausaRepository pausaRepository;
   private final ViajeService viajeService;
   @Autowired
   public PausaService(PausaRepository pausaRepository, ViajeService viajeService){
       this.pausaRepository= pausaRepository;
       this.viajeService = viajeService;
   }


    public Pausa crearPausa(int idViaje) {
        // Busca el viaje por su ID
        Viaje viajeActual= viajeService.findById(idViaje);

        if (viajeActual != null) {
            Pausa pausa = new Pausa();
            pausa.setHoraInicioPausa(new Time(System.currentTimeMillis()));
            pausa.setEstadoDeLaPausa("pausado");
            pausa.setViaje(viajeActual);

            // Guarda la pausa en la base de datos
            pausaRepository.save(pausa);

            // Agrega la pausa al viaje
            viajeActual .getPausas().add(pausa);

            // Actualiza el viaje
            //viajeService.actualizarViaje(viaje);

            return pausa;
        } else {
            // Maneja el caso en el que el viaje no se encuentra
            return null;
        }
    }

    

    /*public Viaje pausarViaje(int id) {
        Viaje viajeActual = viajeRepository.findById(id).orElse(null);

        if(viajeActual != null){
            Pausa pausa = new Pausa();
            pausa.setHoraInicioPausa(new Time(System.currentTimeMillis()));
            pausa.setEstadoDeLaPausa("pausado");
            pausa.setViaje(viajeActual);
            viajeActual.getPausas().add(pausa);
            viajeRepository.save(viajeActual);
        }
        return viajeActual;

    }*/
}

