package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
public class ViajeController {
    @Autowired
    private ViajeRepository viajeRespository;

    //recupero todos los viajes
    @GetMapping("/viajes")
    public Iterable<Viaje>getAllViajes(){
        return viajeRespository.findAll();
    }
    //Recupero viajes por id
    @GetMapping("viaje/id/{id}")
    public Viaje buscarPorId(@PathVariable int id){
        return viajeRespository.findById(id).orElse(null);
    }
    //Filtro de viajes por años
    @GetMapping("/viajes/fecha/{fecha}")
    public List<Viaje> findAllViajesByYear(@PathVariable int fecha){
        return viajeRespository.findAllViajesByYear(fecha);
    }
    //Creo un nuevo viaje
    @PostMapping("/nuevoViaje")
    public Viaje crearNuevoViaje(@RequestBody Viaje viaje){
        return viajeRespository.save(viaje);
    }
    //Elimino un viaje
    @DeleteMapping("/eliminarViaje/{id}")
    public void eliminarViaje(@PathVariable int id){
        viajeRespository.deleteById(id);
    }


}
