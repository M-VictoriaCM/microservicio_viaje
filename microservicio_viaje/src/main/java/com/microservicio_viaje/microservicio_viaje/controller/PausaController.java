package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.repository.PausaRepository;
import com.microservicio_viaje.microservicio_viaje.service.PausaService;
import com.microservicio_viaje.microservicio_viaje.service.ViajeService;
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
    @Autowired
    private PausaRepository pausaRepository;
    @Autowired
    private ViajeService viajeService;

    @GetMapping("/pausas")
    public List<Pausa> getAll(){
        return pausaService.getAllPausa();
    }
    @GetMapping("/pausa/id/{id}")
    public Pausa get(@PathVariable int id){
        return pausaService.getId(id);
    }

    @PostMapping("/crearPausa/{idViaje}")
    public Pausa crearPausa(@PathVariable int idViaje) {
        return pausaService.crearPausa(idViaje);
    }
    @PutMapping("/cancelarPausa/{pausaId}")
    public ResponseEntity<Pausa> cancelarPausa(@PathVariable int pausaId) {
        Pausa pausaCancelada = pausaService.cancelarPausa(pausaId);
        if (pausaCancelada != null) {
            return new ResponseEntity<>(pausaCancelada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/asociarPausaAViaje/{idPausa}/{idViaje}")
    public ResponseEntity<String> asociarPausaAViaje(@PathVariable int idPausa, @PathVariable int idViaje) {
        Pausa pausa = pausaService.getId(idPausa);
        Viaje viaje = viajeService.getId(idViaje);

        if (pausa != null && viaje != null) {
            pausa.setViaje(viaje); // Asocia la pausa al viaje
            pausaRepository.save(pausa);

            return ResponseEntity.ok("Pausa asociada al viaje exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("pausa/idViaje/{idViaje}")
    public List<Pausa> findPausaByViaje(@PathVariable("idViaje") int idViaje){
        Viaje viaje = viajeService.getId(idViaje);
        if (viaje != null) {
            return pausaService.findPausaByViaje(idViaje);
        }
        return null;
    }
}
