package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.EstadoPausa;
import com.microservicio_viaje.microservicio_viaje.repository.EstadoPausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
@ResponseStatus(HttpStatus.OK)
public class EstadopausaController {
    @Autowired
    private EstadoPausaRepository estadoPausaRepository;

    //recupero todos los estados de la pausa
    @GetMapping("/estadosPausa")
    public Iterable<EstadoPausa> getAllEstadoPausa(){
        return estadoPausaRepository.findAll();
    }
    //Recupera los estados de las pausas por id
    @GetMapping("estadoPausa/id/{id}")
    public EstadoPausa buscarEstadosPorId(@PathVariable int id){
        return estadoPausaRepository.findById(id).orElse(null);
    }
    //Busqueda por estado
    @GetMapping("estadoPausa/{estaadoPausa}")
    public EstadoPausa findByEstado(@PathVariable String estado){
        return estadoPausaRepository.findByEstado(estado);
    }
    //creo un nuevo estado
    @PostMapping("/estadoNuevo")
    public EstadoPausa crearNuevoEstado(@RequestBody EstadoPausa estado){
        return estadoPausaRepository.save(estado);
    }
    //modifico un estado de la pausa

    //Eliminacion de los estados
    @DeleteMapping("/eliminarEstado/{id}")
    public void eliminarEstadoPausa(@PathVariable int id){
        estadoPausaRepository.deleteById(id);
    }
}
