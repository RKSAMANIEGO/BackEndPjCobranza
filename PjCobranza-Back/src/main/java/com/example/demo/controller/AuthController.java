package com.example.demo.controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.dto.AuthRequestDto;
import com.example.demo.model.dto.AuthResponseDto;
import com.example.demo.service.IUsuarioService;
import com.example.demo.utils.jwt.Jwt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final IUsuarioService usuarioService;
	private final Jwt utils;
	
	@PostMapping("/log-in")
	@CrossOrigin(origins = "http://localhost:5173")
	ResponseEntity<AuthResponseDto> logIn(@Valid @RequestBody AuthRequestDto  credenciales){	
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credenciales.getEmail(), credenciales.getPassword()));
		
			String token = utils.createToken(credenciales.getEmail());
			
			AuthResponseDto response= new AuthResponseDto("Authenticacion Correcta", credenciales.getEmail(), token);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (AuthenticationException e) {
			throw new AuthorizationDeniedException("Credenciales Incorrectas");
		}	
	}
	
	@PutMapping("/onChangePassword")
	ResponseEntity<Map<String, String>> onChangePassword(@RequestParam(name="email")String correo, @RequestParam(name="password")String pass, @RequestParam(name="newPass") String newPassword ){
		return new ResponseEntity<>(usuarioService.onChangePassword(correo, pass, newPassword),HttpStatus.OK);		
	}
	
}
