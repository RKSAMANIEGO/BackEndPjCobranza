package com.example.demo.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value= {"prestamo_id","clientes","monto","tiempo","tasa_interes","importeTotal","fecha_inicio","fecha_vencimiento","estado"})
public class ListPrestamosDto {

	private Integer prestamo_id;

	private String clientes;

	private BigDecimal monto;

	private Integer tiempo;

	private BigDecimal tasa_interes;

	private Date fecha_inicio;

	private Date fecha_vencimiento;
	
	private String estado;
	
	private BigDecimal importeTotal;
	
}
