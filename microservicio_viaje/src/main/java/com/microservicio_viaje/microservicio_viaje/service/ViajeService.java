package com.microservicio_viaje.microservicio_viaje.service;;

import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.model.Tarifa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.PausaRepository;
import com.microservicio_viaje.microservicio_viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ViajeService {
    private final ViajeRepository viajeRepository;
    private final PausaService pausaService;
    @Autowired
    public ViajeService(ViajeRepository viajeRepository, PausaService pausaService) {
        this.viajeRepository = viajeRepository;
        this.pausaService = pausaService;
    }


    //@Autowired
   // private CreditoService creditoService;
    public Viaje iniciarViaje(Viaje nuevoViaje) {
        Tarifa tarifa = new Tarifa();
        nuevoViaje.setHoraInicioViaje(new Time(System.currentTimeMillis()));
        nuevoViaje.setFechaDelViaje(new Timestamp(System.currentTimeMillis()));
        nuevoViaje.setFinalizado(false);
        viajeRepository.save(nuevoViaje);
        return nuevoViaje;

        //Costo del viaje
        //creditoService.reducirCredito(nuevoViaje.getId(), tarifa);
    }

    public Viaje findById(int id) {
        return viajeRepository.findById(id).orElse(null);
    }

    public List<Viaje> findAll() {
        return viajeRepository.findAll();
    }

    public Viaje pausarViaje(int id) {
        //Llamo al metodo de pausaService, para asociar la pausa al viaje
        Pausa pausa = pausaService.crearPausa(id);
        if(pausa != null){
            Viaje viajeActual = viajeRepository.findById(id).orElse(null);
            if(viajeActual!=null){
                return viajeActual;
            }
        }
        return null;
    }


    /**private final ViajeRepository viajeRepository;
    private final TarifaRepository tarifaRepository;
    private final PausaService pausaService;


    public ViajeService(ViajeRepository viajeRepository, TarifaRepository tarifaRepository, PausaService pausaService) {
        this.viajeRepository = viajeRepository;
        this.pausaService = pausaService;
        this.tarifaRepository = tarifaRepository;
    }
    public Viaje iniciarViaje(ViajeDTO viaje) {
        //creo una instancia de la entidad viaje y configurar sus datos
        Viaje nuevoViaje = new Viaje();
        Tarifa tarifa = obtenerTarifaActual();
        nuevoViaje.setOrigenDelViaje(viaje.getOrigenDelViaje());
        nuevoViaje.setFechaDelViaje(viaje.getFechaDelViaje());
        nuevoViaje.setHoraInicioViaje(viaje.getHoraInicioViaje());
        //nuevoViaje.setTarifa((List<Tarifa>) tarifa);
        //Guardamos el nuevo viaje
        nuevoViaje = viajeRepository.save(nuevoViaje);
        return nuevoViaje;
    }

    private Tarifa obtenerTarifaActual() {
        Tarifa tarifa = tarifaRepository.findPrecioPorMinuto();
        return tarifa;
    }

    public Viaje finalizarViaje(ViajeDTO viajeDTO) {
        Viaje viaje = viajeRepository.findPorId(viajeDTO.getId());
        Viaje viajeEnCurso = new Viaje();
        if (viajeEnCurso.isFinalizado()){
            System.out.println("El viaje ya ha finalizado");
        }
        //Marco el viaje como finalizado
        viajeEnCurso.setFinalizado(true);
        //Calcular el tiempo total de pausa y el tiempo del viaje
        //long tiempoTotalDePausa = calcularTiempoTotalPausas(viajeEnCurso);
        //long tiempoTotalDelViaje = calcularElTiempoTotalDelViaje(viajeEnCurso);
        //en coso de cobro

        //Asosciar tiempo total de pausas, y tiempo de viaje
        viajeEnCurso.setDestinoDelViaje(viajeEnCurso.getDestinoDelViaje());
        viajeEnCurso.setHoraFinViaje(viajeEnCurso.getHoraFinViaje());
        viajeEnCurso.setFechaDelViaje(viajeEnCurso.getFechaDelViaje());
        //guardamos el viaje
        viajeRepository.save(viajeEnCurso);

        return viajeEnCurso;
    }**/
}
