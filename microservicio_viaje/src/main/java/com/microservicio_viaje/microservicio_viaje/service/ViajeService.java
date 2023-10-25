package com.microservicio_viaje.microservicio_viaje.service;;

import com.microservicio_viaje.microservicio_viaje.model.EstadoPausa;
import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.EstadoPausaRepository;
import com.microservicio_viaje.microservicio_viaje.repository.ViajeRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ViajeService {
    private final ViajeRepository viajeRepository;
    private final EstadoPausaRepository estadoPausaRepository;

    public ViajeService(ViajeRepository viajeRepository, EstadoPausaRepository estadoPausaRepository) {
        this.viajeRepository = viajeRepository;
        this.estadoPausaRepository = estadoPausaRepository;
    }

    public Viaje crearPausa(Viaje viaje){
        Pausa pausa = crearNuevaPausa();
        asociarPausaConViaje(viaje, pausa);
        long tiempoDeUsoHastaLaPausa = calcularTiempoDeUsoHastaPausa(viaje, pausa);
        return viajeRepository.save(viaje);
    }

    private Long calcularTiempoDeUsoHastaPausa(Viaje viaje, Pausa pausa) {
        Timestamp horaInicioViaje = viaje.getHoraInicioViaje();
        Timestamp horaInicioPausa = pausa.getHora_inicio();
        return horaInicioPausa.getTime() - horaInicioViaje.getTime();
    }

    private void asociarPausaConViaje(Viaje viaje, Pausa pausa) {
        pausa.setViaje(viaje);
        viaje.getPausas().add(pausa);
    }

    private Pausa crearNuevaPausa() {
        EstadoPausa estadoPausado=estadoPausaRepository.findByEstado("PAUSADO");
        Pausa pausa = new Pausa();
        pausa.setHora_inicio(new Timestamp(System.currentTimeMillis()));
        pausa.setEstado(estadoPausado);
        return pausa;
    }
    public Viaje reanudarViaje(Viaje viaje, Pausa pausa){
        Pausa reanudar = crearNuevaPausaReanudada(pausa);
        asociarPausaConViaje(viaje, reanudar);
        long tiempoDeUsoDesdeReanudacion=  calcularTiempoTrancurridoDesdeReanudacion(reanudar);

        return viajeRepository.save(viaje);
    }

    private long calcularTiempoTrancurridoDesdeReanudacion(Pausa reanudar) {
        Timestamp horaReanudacion = reanudar.getHora_fin();
        Timestamp horaActual = new Timestamp(System.currentTimeMillis());
        return horaActual.getTime() - horaReanudacion.getTime();
    }

    private Pausa crearNuevaPausaReanudada(Pausa pausa) {
        EstadoPausa estadoActivo = estadoPausaRepository.findByEstado("ACTIVO");
        Pausa nuevaInstacia = new Pausa();
        nuevaInstacia.setHora_inicio(pausa.getHora_inicio());
        nuevaInstacia.setEstado(estadoActivo);
        return nuevaInstacia;
    }
}
