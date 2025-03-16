package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usuarios")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="email", unique=true , nullable = false)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="confirm_password", nullable = false)
	private String confirmPassword;
	
	@Column(name="rol", nullable = false)
	private String rol;
}
