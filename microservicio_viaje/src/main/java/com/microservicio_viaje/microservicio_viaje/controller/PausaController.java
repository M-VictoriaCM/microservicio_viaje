package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.DTO.PausaDTO;
import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.PausaRepository;
import com.microservicio_viaje.microservicio_viaje.service.PausaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
public class PausaController {
    @Autowired
    private PausaService pausaService;

    @PostMapping("/crearPausa/{idViaje}")
    public ResponseEntity<?> crearPausa(@PathVariable int idViaje) {
        Pausa pausa = pausaService.crearPausa(idViaje);
        if (pausa != null) {
            return new ResponseEntity<>(pausa, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("No se encontró el viaje con ID " + idViaje, HttpStatus.NOT_FOUND);
        }
    }


}
