package com.microservicio_viaje.microservicio_viaje.service;;

import com.microservicio_viaje.microservicio_viaje.model.EstadoPausa;
import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.EstadoPausaRepository;
import com.microservicio_viaje.microservicio_viaje.repository.ViajeRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;

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
        Time horaInicioViaje = viaje.getHoraInicioViaje();
        Time horaInicioPausa = pausa.getHoraInicioPausa();
        return horaInicioPausa.getTime() - horaInicioViaje.getTime();
    }

    private void asociarPausaConViaje(Viaje viaje, Pausa pausa) {
        pausa.setViaje(viaje);
        viaje.getPausas().add(pausa);
    }

    /**
     *
     * @return
     */
    private Pausa crearNuevaPausa() {
        EstadoPausa estadoPausado= estadoPausaRepository.findByEstado("PAUSADO");
        Pausa pausa = new Pausa();
        pausa.setHoraInicioPausa(new Time(System.currentTimeMillis()));
        pausa.setEstado(estadoPausado);
        return pausa;
    }
    public Viaje reanudarViaje(Viaje viaje, Pausa pausa){
        Pausa reanudar = crearNuevaPausaReanudada(pausa);
        asociarPausaConViaje(viaje, reanudar);
        long tiempoDeUsoDesdeReanudacion=  calcularTiempoTrancurridoDesdeReanudacion(reanudar);

        return viajeRepository.save(viaje);
    }

    /**
     *
     * @param reanudar
     * @return
     */
    private long calcularTiempoTrancurridoDesdeReanudacion(Pausa reanudar) {
        Time horaReanudacion = reanudar.getHoraFinPausa();
        Time horaActual = new Time(System.currentTimeMillis());
        return horaActual.getTime() - horaReanudacion.getTime();
    }

    private Pausa crearNuevaPausaReanudada(Pausa pausa) {
        EstadoPausa estadoActivo = estadoPausaRepository.findByEstado("ACTIVO");
        Pausa nuevaInstacia = new Pausa();
        nuevaInstacia.setHoraInicioPausa(pausa.getHoraInicioPausa());
        nuevaInstacia.setEstado(estadoActivo);
        return nuevaInstacia;
    }
}
