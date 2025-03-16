package com.example.demo.model.dto;

import com.example.demo.model.CPrestamo;
import com.example.demo.model.enumerated.EnumMetodoPago;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDto {
 
	@NotBlank(message="El Monto de Pago no puede estar Vacia ni ser Nulo.")
	private Double montoPago;

	@NotBlank(message="El Metodo de Pago no puede estar Vacia ni ser Nulo.")
	private EnumMetodoPago metodoPago;
	
	@NotBlank(message="Ingrese El Prestamo para realizar el Pago.")
	private CPrestamo prestamo;
	
}
