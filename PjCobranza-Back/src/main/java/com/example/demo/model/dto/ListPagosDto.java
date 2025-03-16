package com.example.demo.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPagosDto {

	private Integer pago_id;

	private String cliente;
	
	private BigDecimal monto;
	
	private Integer tiempo;
	
	private BigDecimal tasa_interes;
	
	private BigDecimal monto_pago;
	
	private Date fecha_pago;
	
	private String metodo_pago;
}
