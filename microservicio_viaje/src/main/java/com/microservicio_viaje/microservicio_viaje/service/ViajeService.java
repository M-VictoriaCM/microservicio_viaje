package com.microservicio_viaje.microservicio_viaje.service;;

import com.microservicio_viaje.microservicio_viaje.DTO.ViajeDTO;
import com.microservicio_viaje.microservicio_viaje.model.Monopatin;
import com.microservicio_viaje.microservicio_viaje.model.Tarifa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.TarifaRepository;
import com.microservicio_viaje.microservicio_viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViajeService {
    @Autowired
    private final ViajeRepository viajeRepository;
    @Autowired
    private final TarifaRepository tarifaRepository;

    public ViajeService(ViajeRepository viajeRepository, TarifaRepository tarifaRepository) {
        this.viajeRepository = viajeRepository;
        this.tarifaRepository = tarifaRepository;
    }

    public List<Viaje> getAllViajes() {
        return viajeRepository.findAll();
    }

    public Viaje getId(int id) {
        return viajeRepository.findById(id).orElse(null);
    }

    public void create(ViajeDTO nuevo) {
        Viaje viaje = new Viaje();
        Optional<Tarifa> tarifa = tarifaRepository.findById(1);
        viaje.setOrigenDelViaje(nuevo.getOrigenDelViaje());
        viaje.setFechaDelViaje(new Timestamp(System.currentTimeMillis()));
        viaje.setHoraInicioViaje(new Time(System.currentTimeMillis()));
        viaje.setFinalizado(false);
        viaje.addTarifaInicial(tarifa.get());
        viajeRepository.save(viaje);

    }

    public double calcularFacturadoEnRango(int anio, int mesInicio, int mesFin) {
        List<Viaje> viajeEnRango = viajeRepository.finByRangoDeMes(anio, mesInicio, mesFin);
        double totalFacturado = 0.0;
        for (Viaje v : viajeEnRango) {
            totalFacturado += v.getCobroViaje();
        }
        return totalFacturado;
    }

    public List<Viaje> findViajesConPausa() {
        return viajeRepository.findViajesConPausa();
    }

    public List<Viaje> findAllViajesByYear(int anio) {
        return viajeRepository.findAllViajesByYear(anio);
    }

    public List<Monopatin> cantidadDeViajesPorAnio(int cantidadViajes, int anio) {
        List<Monopatin> monopatinesFiltrados = new ArrayList<>();

        List<Viaje> lista = viajeRepository.findAllViajesByYear(anio);
        for (Viaje v : lista) {
            if (v.getMonopatin().getCantViajes() > cantidadViajes) {
                monopatinesFiltrados.add(v.getMonopatin());
            }
        }

        return monopatinesFiltrados;
    }
}
