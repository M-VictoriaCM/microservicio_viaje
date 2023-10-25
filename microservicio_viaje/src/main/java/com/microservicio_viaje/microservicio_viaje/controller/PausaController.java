package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.repository.PausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service_viaje")
public class PausaController {
    @Autowired
    private PausaRepository pausaRepository;

    //Creo una pausa
    @PostMapping("/creoUnaPausa")
    public Pausa creacionPausa(@RequestBody Pausa pausa){
        return pausaRepository.save(pausa);
    }

}
