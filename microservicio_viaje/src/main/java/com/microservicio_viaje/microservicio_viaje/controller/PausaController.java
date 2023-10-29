package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.EstadoPausa;
import com.microservicio_viaje.microservicio_viaje.model.Pausa;
import com.microservicio_viaje.microservicio_viaje.repository.PausaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
public class PausaController {
    @Autowired
    private PausaRepository pausaRepository;

    /**
     * Creacion de una pausa
     * @param pausa
     * @return una respuesta
     */
    @PostMapping("/creoUnaPausa")
    public ResponseEntity<String>create(@RequestBody Pausa pausa) {
        try {
            pausaRepository.save(pausa);
            return ResponseEntity.ok("Se agregó con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Lamentablemente no se posible agregar el dato "+e.getMessage());
        }
    }

    /**
     *
     * @return Muestro todas pausas
     */
    @GetMapping("/pausa")
    public List<Pausa> getAll() {
        return pausaRepository.findAll();
    }

    /**
     * @param id
     * @return pausa asociada a la id
     */
    @GetMapping("/pausa/id/{id}")
    public Pausa get(@PathVariable int id) {
        return pausaRepository.findById(id).orElse(null);
    }

    /**
     * @param id
     * @param nuevoEstado
     * @return una pausa actualizada
     */
    @PutMapping("/actualizarEstadoPausa/{id}")
    public ResponseEntity<String>update(@PathVariable int id, @RequestBody EstadoPausa nuevoEstado){
        Pausa pausaExistente = pausaRepository.findById(id).orElse(null);
        if(pausaExistente != null){
            pausaExistente.setEstado(nuevoEstado);
            pausaRepository.save(pausaExistente);
            return ResponseEntity.ok("Se modificó con éxito");
        }else{
            return ResponseEntity.badRequest().body("Error al actualizar el estado");
        }
    }

    /**
     * Metodo para eliminar una pausa
     * @param id
     * @return un mensaje del servidor
     */
    @DeleteMapping("/elimnarPausa/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        try{
            pausaRepository.deleteById(id);
            return ResponseEntity.ok("Se elimino con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo eliminar "+e.getMessage());
        }

    }


}
