package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.microservicio_viaje.service.JwtService;
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
    @Autowired
    JwtService jwtService;

    @GetMapping("/viajes")
    public List<Viaje>getAll(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            return viajeService.getAllViajes();
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;
        }
    }
    @GetMapping("/viaje/id/{id}")
    public Viaje get(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int id){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            return viajeService.getId(id);
        }else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;
        }
    }
    @PostMapping("/inicioViaje")
    public void create(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Viaje nuevo){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            viajeService.create(nuevo);
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
    }
    /*@PostMapping("/inicioViaje")
    public void create(@RequestBody Viaje nuevo){
        viajeService.create(nuevo);
    }*/
    @PutMapping("/finalizarViaje/{viajeId}")
    public ResponseEntity<Viaje> finalizarViaje(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int viajeId, @RequestBody Viaje viajeFinalizado) {
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            if (viajeFinalizado != null) {
                Viaje viaje = viajeService.finalizarViaje(viajeId, viajeFinalizado);
                return new ResponseEntity<>(viajeFinalizado, HttpStatus.OK);
            }
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        return null;
    }

    @GetMapping("/facturacion/{anio}/{mesInicio}/{mesFin}")
    public double calcularFacturadoEnRango(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int anio, @PathVariable int mesInicio,@PathVariable int mesFin) {
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            return viajeService.calcularFacturadoEnRango(anio, mesInicio, mesFin);
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return 0.0;
        }
    }
    @GetMapping("/viajesConPausas")
    public List<Viaje>getViajeConpausas(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            return viajeService.findViajesConPausa();
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;
        }
    }
    @GetMapping("/viajeSinpausa")
    public List<Viaje>getViajeSinPausas(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            List<Viaje> todoslosViajes = viajeService.getAllViajes();
            List<Viaje> viajesConPausas = viajeService.findViajesConPausa();
            //ahora que necesito quedame con los viajes que no poseen pausas asociadas
            todoslosViajes.removeAll(viajesConPausas);
            return todoslosViajes;
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;
        }
    }
    @GetMapping("/viajesPorAnio/{anio}")
    public List<Viaje>findAllViajesByYear(@RequestHeader("Authorization") String authorizationHeader,@PathVariable int anio){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            return viajeService.findAllViajesByYear(anio);
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;

        }
    }

}
