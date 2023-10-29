package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.EstadoPausa;
import com.microservicio_viaje.microservicio_viaje.repository.EstadoPausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service_viaje")
@ResponseStatus(HttpStatus.OK)
public class EstadopausaController {
    @Autowired
    private EstadoPausaRepository estadoPausaRepository;

    /**
     *
     * @return todos los estados de la pausa
     */
    @GetMapping("/estadosPausa")
    public Iterable<EstadoPausa> getAllEstadoPausa(){
        return estadoPausaRepository.findAll();
    }

    /**
     *
     * @param id
     * @return estado de la pausa asociado
     */
    @GetMapping("estadoPausa/id/{id}")
    public EstadoPausa buscarEstadosPorId(@PathVariable int id){
        return estadoPausaRepository.findById(id).orElse(null);
    }

    /**
     *
     * @param estado
     * @return la informacion de ese estado
     */
    @GetMapping("/estadoPausa/porEstado/{estado}")
    public EstadoPausa findByEstado(@PathVariable String estado){
        return estadoPausaRepository.findByEstado(estado);
    }

    /**
     * Creacion de un nuevo estado
     * @param estado
     * @return una respuesta del servidor
     */
    @PostMapping("/nuevaPausa")
    public ResponseEntity<String>  crearNuevoEstado(@RequestBody EstadoPausa estado){
        try{
            estadoPausaRepository.save(estado);
            return ResponseEntity.ok("Se agregó con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Lamentablemente no se posible agregar el dato "+e.getMessage());
        }

    }

    /**
     * Actualizacion del estado
     * @param id
     * @param estado
     * @return una respuesta del servidor
     */
    @PutMapping("/actualizarEstado/{id}")
    public ResponseEntity<String> actualizarEstado(@PathVariable int id, @RequestBody EstadoPausa estado) {
            EstadoPausa estadoExistente = estadoPausaRepository.findById(id).orElse(null);
            if(estadoExistente != null){
                estadoExistente.setEstadoDeLaPausa(estado.getEstadoDeLaPausa());
                estadoPausaRepository.save(estadoExistente);
                return ResponseEntity.ok("Se modificó con éxito");
            }else{
                return ResponseEntity.badRequest().body("Error al actualizar el estado");
            }
    }

    /**
     * Eliminacion de un estado por id
     * @param id
     */
    @DeleteMapping("/eliminarEstado/{id}")
    public ResponseEntity<String> eliminarEstadoPausa(@PathVariable int id){
        try{
            estadoPausaRepository.deleteById(id);
            return ResponseEntity.ok("Se elimino con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo eliminar "+e.getMessage());
        }

    }
}
