package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.EstadoPausa;
import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.repository.PausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
public class PausaController {
    @Autowired
    private PausaRepository pausaRepository;

    //Creo una pausa
    @PostMapping("/creoUnaPausa")
    public Pausa creacionPausa(@RequestBody Pausa pausa) {
        return pausaRepository.save(pausa);
    }

    //Obtengo todas las pausas
    @GetMapping("/pausa")
    public List<Pausa> getAllPausa() {
        return pausaRepository.findAll();
    }

    //Mostrar pausas por id
    @GetMapping("/pausa/id/{id}")
    public Pausa buscoPausasPorId(@PathVariable int id) {
        return pausaRepository.findById(id).orElse(null);
    }
    //Actualizo el estado de una pausa
   @PutMapping("/actualizarEstadoPausa/{id}")
    public Pausa actualizarEstadoPausa(@PathVariable int id, @RequestBody EstadoPausa nuevoEstado){
        return pausaRepository.findById(id)
                .map(pausaExistente -> {
                    pausaExistente.setEstado(nuevoEstado);
            return pausaRepository.save(pausaExistente);
        }).orElse(null);
    }

}
