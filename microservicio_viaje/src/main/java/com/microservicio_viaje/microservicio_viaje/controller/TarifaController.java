package com.microservicio_viaje.microservicio_viaje.controller;

import com.microservicio_viaje.microservicio_viaje.model.Tarifa;
import com.microservicio_viaje.microservicio_viaje.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_viaje")
public class TarifaController {
    @Autowired
    private TarifaRepository tarifaRepository;

    /**
     * Creacion de una nueva tarifa
     * @param tarifa
     * @return
     */
    @PostMapping("/CreoUnaTarifa")
    public ResponseEntity<String> create(@RequestBody Tarifa tarifa){
        try {
            tarifaRepository.save(tarifa);
            return ResponseEntity.ok("Se agregó con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Lamentablemente no se posible agregar el dato "+e.getMessage());
        }
    }

    /**
     *
     * @return Muestro todas las tarifas
     */
    @GetMapping("/tarifa")
    public List<Tarifa> getAll(){
        return tarifaRepository.findAll();
    }

    /**
     *
     * @param id
     * @return tarifa asociada a la id
     */
    @GetMapping("/tarifa/id/{id}")
    public Tarifa get(@PathVariable int id){
        return tarifaRepository.findById(id).orElse(null);
    }

    /**
     * actualizacion de tarifa por minuto
     * @param id
     * @param tarifa
     * @return respuesta del servidor
     */
    @PutMapping("/actualizarTarifaPorMinuto/{id}")
    public ResponseEntity<String> updateTarifaPorMinuto(@PathVariable int id, @RequestBody Tarifa tarifa){
         Tarifa tarifaExistentePorMin = tarifaRepository.findById(id).orElse(null);
         if (tarifaExistentePorMin !=null){
             tarifaExistentePorMin.setPrecioPorMinuto(tarifa.getPrecioPorMinuto());
             tarifaRepository.save(tarifaExistentePorMin);
             return ResponseEntity.ok("Se modificó con éxito");
         }else{
             return ResponseEntity.badRequest().body("Error al actualizar el estado");
         }
    }

    /**
     * Actualizacion en la tarifa extra
     * @param id
     * @param tarifa
     * @return respuesta del servidor
     */
    @PutMapping("/actualizarTarifaExtra/{id}")
    public ResponseEntity<String>  updateTarifaExtra(@PathVariable int id, @RequestBody Tarifa tarifa){
        Tarifa tarifaExistenteExtra = tarifaRepository.findById(id).orElse(null);
        if (tarifaExistenteExtra !=null){
            tarifaExistenteExtra.setTarifaExtraPorPausaExtensa(tarifa.getTarifaExtraPorPausaExtensa());
            tarifaRepository.save(tarifaExistenteExtra);
            return ResponseEntity.ok("Se modificó con éxito");
        }else{
            return ResponseEntity.badRequest().body("Error al actualizar el estado");
        }
    }

    /**
     * Metodo eliminar
     * @param id
     * @return una repuesta del servidor
     */
    @DeleteMapping("eliminarTarifa/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        try{
            tarifaRepository.deleteById(id);
            return ResponseEntity.ok("Se elimino con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo eliminar "+e.getMessage());
        }
    }

}
