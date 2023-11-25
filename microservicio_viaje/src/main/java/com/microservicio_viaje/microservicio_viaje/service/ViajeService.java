package com.microservicio_viaje.microservicio_viaje.service;;


import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.model.Tarifa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.PausaRepository;
import com.microservicio_viaje.microservicio_viaje.repository.TarifaRepository;
import com.microservicio_viaje.microservicio_viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ViajeService {
    @Autowired
    private final ViajeRepository viajeRepository;
    @Autowired
    private final PausaRepository pausaRepository;
    @Autowired
    private final TarifaRepository tarifaRepository;
    @Autowired
    JwtService jwtService;

    public ViajeService(ViajeRepository viajeRepository, PausaRepository pausaRepository, TarifaRepository tarifaRepository) {
        this.viajeRepository = viajeRepository;
        this.pausaRepository = pausaRepository;
        this.tarifaRepository = tarifaRepository;
    }

    public List<Viaje> getAllViajes() {
        return viajeRepository.findAll();
    }

    public Viaje getId(int id) {
        return viajeRepository.findById(id).orElse(null);
    }

    public void create(Viaje nuevo, String token) {
        if(jwtService.isTokenValid(token)) {
            Viaje viaje = new Viaje();
            Optional<Tarifa> tarifa = tarifaRepository.findById(1);
            viaje.setOrigenDelViaje(nuevo.getOrigenDelViaje());
            viaje.setFechaDelViaje(new Timestamp(System.currentTimeMillis()));
            viaje.setHoraInicioViaje(new Time(System.currentTimeMillis()));
            viaje.setFinalizado(false);
            viaje.addTarifaInicial(tarifa.get());
            viajeRepository.save(viaje);
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
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

    public Viaje finalizarViaje(int viajeId, Viaje viaje, String token) {
        if(jwtService.isTokenValid(token)) {
            Optional<Viaje> viajeOptional = viajeRepository.findById(viajeId);
            if (viajeOptional.isPresent()) {
                Viaje viajeFinalizado = viajeOptional.get();
                viajeFinalizado.setDestinoDelViaje(viaje.getDestinoDelViaje());
                viajeFinalizado.setHoraFinViaje(new Time(System.currentTimeMillis()));
                viajeFinalizado.setFinalizado(true);

                //obtengo las pausas del viaje
                List<Pausa> pausasViaje = obtenerpausasDelViaje(viajeFinalizado);
                //calculo el costo del viaje
                int costoDelViaje = calcularCostoDelViaje(viajeFinalizado, pausasViaje);
                viajeFinalizado.setCobroViaje(costoDelViaje);
                //guardo el viaje
                viajeRepository.save(viajeFinalizado);
                return viajeFinalizado;
            }
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        return null;
    }
    private int calcularCostoDelViaje(Viaje viaje, List<Pausa> pausasViaje) {
        int tiempoUso = calcularTiempoDeUso(viaje);
        int costo;
        //calcular el tiempo total de las pausas
        int tiempoTotalPausas= calcularTiempoTotalpausa(pausasViaje);
        if(tiempoTotalPausas > 15){
            costo= costoPausaExtendida(tiempoUso, tiempoTotalPausas);
            return costo;
        }
        costo = costoSinPausa(tiempoUso);
        return costo;
    }
    private int costoSinPausa(double tiempoUso) {
        double tarifa = tarifaRepository.findTarifaByTipo("precioPorMinuto");
        double costo =tiempoUso * tarifa;
        String.format("%.2f", Math.abs(costo));

        return (int)costo;
    }

    private int costoPausaExtendida(double tiempoUso, double tiempoTotalPausas) {
        double tarifaExtra = tarifaRepository.findTarifaByTipo("precioExtra");
        double tarifaBase = tarifaRepository.findTarifaByTipo("precioPorMinuto");
        double costoBase = tiempoUso * tarifaBase;
        double porPausaExtra= tiempoTotalPausas * tarifaExtra;
        double costoFinal= costoBase + porPausaExtra;
        String.format("%.2f", Math.abs(costoFinal));

        return (int)costoFinal;
    }

    private int calcularTiempoTotalpausa(List<Pausa> pausasViaje) {
        double tiempoTotalPausas = 0.0;
        for(Pausa pausa:pausasViaje){
            int duracionPausa= calcularDuracionEnMinutos(pausa);
            tiempoTotalPausas += duracionPausa;
        }

        return (int)tiempoTotalPausas;
    }

    private int calcularDuracionEnMinutos(Pausa pausa) {
        Time horaInicioPausa = pausa.getHoraInicioPausa();
        Time horaFinPausa= pausa.getHoraFinPausa();

        if(horaFinPausa !=null && horaInicioPausa != null){
            int fin= horaFinPausa.getMinutes();
            int inicio = horaInicioPausa.getMinutes();
            int duracionPausaEnmilisegundos= (fin - inicio);

            return Math.abs(duracionPausaEnmilisegundos);
        }
        return 0; //si el inicio o el fin o ambos son nulos
    }

    private int calcularTiempoDeUso(Viaje viaje) {
        Time horaInicioViaje = viaje.getHoraInicioViaje();
        Time horaFinViaje = viaje.getHoraFinViaje();
        if(horaInicioViaje != null && horaFinViaje != null){
            int fin = horaFinViaje.getMinutes();
            int inicio = horaInicioViaje.getMinutes();
            int tiempoEnMilisegundos= (fin - inicio);

            return Math.abs(tiempoEnMilisegundos);
        }
        return 0;
    }
    private List<Pausa>obtenerpausasDelViaje(Viaje viaje) {
        return pausaRepository.findPausaByViaje(viaje.getId());
    }

}
