package com.example.demo.model;

import java.time.LocalDate;

import com.example.demo.model.enumerated.EnumEstadoPrestamos;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="prestamos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = {"prestamoId","cliente","monto","tiempo","tasaInteres","fechaInicio","fechaVencimiento","estado"})
public class CPrestamo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="prestamo_id")
	private Integer prestamoId;
	
	private Double monto;
	
	private Integer tiempo;
	
	@Column(name="tasa_interes")
	private Double tasaInteres;
	
	@Column(name="fecha_inicio")
	private LocalDate fechaInicio;
	
	@Column(name="fecha_vencimiento")
	private LocalDate fechaVencimiento;
	
	@Enumerated(EnumType.STRING)
	private EnumEstadoPrestamos estado;
	
	@ManyToOne
	@JoinColumn(name= "cliente_id" , nullable = false , foreignKey = @ForeignKey(foreignKeyDefinition = (
	"foreign key (cliente_id) references clientes(cliente_id)"	)))
	private CCliente cliente;
	

}
