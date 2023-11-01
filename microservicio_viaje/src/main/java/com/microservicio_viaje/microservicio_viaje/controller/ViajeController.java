package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;
    @GetMapping("/viajes")
    public List<Viaje>getAll(){
        return viajeService.getAllViajes();
    }
    @GetMapping("/viaje/id/{id}")
    public Viaje get(@PathVariable int id){
        return viajeService.getId(id);
    }
    @PostMapping("/inicioViaje")
    public Viaje create(@RequestBody Viaje nuevo){
        return viajeService.create(nuevo);
    }

}
