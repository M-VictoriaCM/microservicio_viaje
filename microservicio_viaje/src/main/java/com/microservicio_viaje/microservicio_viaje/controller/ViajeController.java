package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.DTO.PausaDTO;
import com.microservicio_viaje.microservicio_viaje.DTO.ViajeDTO;
import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.ViajeRepository;
import com.microservicio_viaje.microservicio_viaje.service.PausaService;
import com.microservicio_viaje.microservicio_viaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;
    private PausaService pausaService;

    @PostMapping("/iniciarViaje")
    public Viaje create(@RequestBody Viaje nuevoViaje){
        return viajeService.iniciarViaje(nuevoViaje);
    }
    @PostMapping("/pausarViaje/id/{id}")
    public Viaje pausarViaje(@PathVariable int id) {
        // Llama al método del servicio PausaService para crear la pausa
        Pausa pausa = pausaService.crearPausa(id);

        if (pausa != null) {
            // La pausa se creó exitosamente, puedes devolver el viaje actualizado o cualquier otra respuesta que necesites
            return viajeService.pausarViaje(id);
        }
        return null;
    }
   @GetMapping("/viaje/id/{id}")
    public Viaje get(@PathVariable int id){
       return viajeService.findById(id);
   }
   @GetMapping("/viajes")
    public List<Viaje>getAll(){
       return viajeService.findAll();
   }
}
