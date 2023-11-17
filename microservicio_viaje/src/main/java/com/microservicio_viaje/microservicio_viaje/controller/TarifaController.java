package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.Tarifa;
import com.microservicio_viaje.microservicio_viaje.repository.TarifaRepository;
import com.microservicio_viaje.microservicio_viaje.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
public class TarifaController {
    @Autowired
    private TarifaRepository tarifaRepository;
    @Autowired
    JwtService jwtService;

    /**
     *
     * @return Muestro todas las tarifas
     */
    @GetMapping("/tarifa")
    public List<Tarifa> getAll(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            return tarifaRepository.findAll();
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;
        }
    }

    /**
     * Creacion de una nueva tarifa
     * @param tarifa
     * @return
     */
    @PostMapping("/CreoUnaTarifa")
    public ResponseEntity<String> create(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Tarifa tarifa){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            tarifaRepository.save(tarifa);
            return ResponseEntity.ok("Se agregó con exito");
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;
        }
    }

    /**
     * @param id
     * @return tarifa asociada a la id
     */
    @GetMapping("/tarifa/id/{id}")
    public Tarifa get(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int id){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            return tarifaRepository.findById(id).orElse(null);
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;
        }
    }

    /**
     * Metodo eliminar
     * @param id
     * @return una repuesta del servidor
     */
    @DeleteMapping("eliminarTarifa/{id}")
    public ResponseEntity<String> delete(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int id){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            tarifaRepository.deleteById(id);
            return ResponseEntity.ok("Se elimino con exito");
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;
        }
    }
    @GetMapping("/tipo/{tipo}")
    public double getPrecioPorMinuto(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String tipo) {
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
            return tarifaRepository.findTarifaByTipo(tipo);
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return 0.0;
        }
    }

    /**
     * Actualiza la tarifa del viaje según el id
     * @param id
     * @param valor
     */
    @PutMapping("actualizarTarifa/{id}/{valor}")
    public ResponseEntity<String> actualizarTarifa(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int id, @PathVariable int valor){
        String token = authorizationHeader.replace("Bearer ", "");
        if(jwtService.isTokenValid(token)) {
        Tarifa tarifa = tarifaRepository.findById(id).orElse(null);
            tarifa.setValor(valor);
            tarifaRepository.save(tarifa);
            return ResponseEntity.ok("El valor de la tarifa "+tarifa.getTipo()+" se actualizó correctamente");
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            return null;
        }
    }
}
