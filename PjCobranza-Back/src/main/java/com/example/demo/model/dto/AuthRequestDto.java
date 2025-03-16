package com.example.demo.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {

	@NotBlank(message="El Correo no puedes estar Vacio ni Nulo")
	@Email(message="Ingrese un Correo Valido")
	private String email;
	
	@NotBlank(message="La Contraseña no puede estar Vacio ni Nulo")
	@Size(min = 8 , max =100)
	private String password;
}
