package com.microservicio_viaje.microservicio_viaje.service;;

import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ViajeService {
    @Autowired
    private final ViajeRepository viajeRepository;

    public ViajeService(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    public List<Viaje> getAllViajes() {
        return viajeRepository.findAll();
    }

    public Viaje getId(int id) {
        return viajeRepository.findById(id).orElse(null);
    }

    public Viaje create(Viaje nuevo) {
        nuevo.setOrigenDelViaje(nuevo.getOrigenDelViaje());
        nuevo.setFechaDelViaje(new Timestamp(System.currentTimeMillis()));
        nuevo.setHoraInicioViaje(new Time(System.currentTimeMillis()));
        nuevo.setFinalizado(false);
        viajeRepository.save(nuevo);

        return nuevo;
    }


}
