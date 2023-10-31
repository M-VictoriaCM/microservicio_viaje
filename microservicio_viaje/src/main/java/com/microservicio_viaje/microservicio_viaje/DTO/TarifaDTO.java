package com.microservicio_viaje.microservicio_viaje.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TarifaDTO {
   private int id;
   private int precioPorMinuto;
   private int tarifaExtraPorpausaExtensa;

}
