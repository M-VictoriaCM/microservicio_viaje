package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.Viaje;
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
    @GetMapping("/viajes")
    public List<Viaje>getAll(){
        return viajeService.getAllViajes();
    }
    @GetMapping("/viaje/id/{id}")
    public Viaje get(@PathVariable int id){
        return viajeService.getId(id);
    }
    @PostMapping("/inicioViaje")
    public void create(@RequestBody Viaje nuevo){
       viajeService.create(nuevo);
    }
    @PutMapping("/finalizarViaje/{viajeId}")
    public ResponseEntity<Viaje> finalizarViaje(@PathVariable int viajeId, @RequestBody Viaje viajeFinalizado) {
        Viaje viaje= viajeService.finalizarViaje(viajeId, viajeFinalizado);
        if (viaje != null) {
            return  new ResponseEntity<>(viajeFinalizado,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/facturacion/{anio}/{mesInicio}/{mesFin}")
    public double calcularFacturadoEnRango(@PathVariable int anio, @PathVariable int mesInicio,@PathVariable int mesFin) {
        return viajeService.calcularFacturadoEnRango(anio, mesInicio, mesFin);
    }
    @GetMapping("/viajesConPausas")
    public List<Viaje>getViajeConpausas(){
        return viajeService.findViajesConPausa();
    }
    @GetMapping("/viajeSinpausa")
    public List<Viaje>getViajeSinPausas(){
        List<Viaje>todoslosViajes = viajeService.getAllViajes();
        List<Viaje>viajesConPausas = viajeService.findViajesConPausa();
        //ahora que necesito quedame con los viajes que no poseen pausas asociadas
        todoslosViajes.removeAll(viajesConPausas);
        return todoslosViajes;
    }
    @GetMapping("/viajesPorAnio/{anio}")
    public List<Viaje>findAllViajesByYear(@PathVariable int anio){
        return viajeService.findAllViajesByYear(anio);
    }


    /*@GetMapping("/cantidaViajesPorAnio/{cantidadViajes}{anio}")
    public List<Monopatin> cantidadDeViajesPorAnio(int cantidadViajes, int anio){
        return viajeService.cantidadDeViajesPorAnio(cantidadViajes, anio);
    }*/

}
