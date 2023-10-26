package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.Tarifa;
import com.microservicio_viaje.microservicio_viaje.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
public class TarifaController {
    @Autowired
    private TarifaRepository tarifaRepository;

    //Creo una tarifa nueva
    @PostMapping("/CreoUnaTarifa")
    public Tarifa creacionTarifa(@RequestBody Tarifa tarifa){
        return tarifaRepository.save(tarifa);
    }
    //Muestro todas las tarifas
    @GetMapping("/tarifa")
    public List<Tarifa> getAllTarifa(){
        return tarifaRepository.findAll();
    }
    //Mostrar tarifas por id
    @GetMapping("/tarifa/id/{id}")
    public Tarifa buscoTarifaPorId(@PathVariable int id){
        return tarifaRepository.findById(id).orElse(null);
    }
    //Actualizar tarifa por minuto
    @PutMapping("/actualizarTarifaPorMinuto/{id}")
    public Tarifa actualizarTarifaPorMinuto(@PathVariable int id, @RequestBody int tarifa){
        return tarifaRepository.findById(id).map(tarifaExistente ->{
            tarifaExistente.setPrecioPorMinuto(tarifa);
            return tarifaRepository.save(tarifaExistente);
        }).orElse(null);
    }

    //Actualizo tarifa
    @PutMapping("/actualizarTarifaExtra/{id}")
    public Tarifa actualizarTarifaExtra(@PathVariable int id, @RequestBody int tarifa){
        return tarifaRepository.findById(id).map(tarifaExistenteExtra ->{
            tarifaExistenteExtra.setTarifaExtraPorPausaExtensa(tarifa);
            return tarifaRepository.save(tarifaExistenteExtra);
        }).orElse(null);
    }

}
