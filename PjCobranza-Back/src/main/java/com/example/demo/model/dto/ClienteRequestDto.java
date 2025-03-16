package com.example.demo.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDto {

	 @NotBlank(message="El nombre no puede ser nulo ni vacio")
	 @Size(min =2 , max=60 , message="El Nombre como minimo debe de tener 2 caracteres")
	 String nombre;
	
	 @NotBlank(message="El apellido no puede ser nulo ni vacio")
	 @Size(min =2 , max=60 , message="El Apellido como minimo debe de tener 2 caracteres")
	 String apellido;

	 @NotBlank(message="El dni no puede ser nulo ni vacio")
	 @Size(min =8 , max=15 , message="El Dni como minimo debe de tener 8 caracteres")
	 String dni;

	 @Email(message="Ingrese un Correo Valido")
	 @Size(min =10 , max=100 , message="El Correo como minimo debe de tener 10 caracteres")
	 String correo;
	
	 @Size(min =9 , max=100 , message="El Celular como minimo debe de tener 9 caracteres")
	 String telefono;
	
	 String direccion;
}
