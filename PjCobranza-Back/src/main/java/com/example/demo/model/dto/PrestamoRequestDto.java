package com.example.demo.model.dto;

import com.example.demo.model.CCliente;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoRequestDto {

	@NotBlank(message="El Monto no puede ser nulo ni vacio")
	private Double monto;
	
	@NotBlank(message="El Tiempo no puede ser nulo ni vacio")
	private Integer tiempo;
	
	@NotBlank(message="La Taza de Interes no puede ser nulo ni vacio")
	private Double tasaInteres;
	
	@NotBlank(message="Ingrese como minimo 1 Cliente, no puede ser nulo ni vacio")
	private CCliente cliente;
}
