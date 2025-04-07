package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="clientes")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cliente_id")
	private Integer clienteId;
	
	@Column(length = 100, nullable = false)
	private String nombre;
	
	@Column(length = 100, nullable = false)
	private String apellido;
	
	@Column(length = 15, nullable = false, unique = true)
	private String dni;
	
	@Column(length=100)
	private String correo;
	
	@Column(length=20)
	private String telefono;
	
	private String direccion;
	
	@Column(name="fecha_registro")
	//private String fechaRegistro;
	private LocalDateTime fechaRegistro;
}
