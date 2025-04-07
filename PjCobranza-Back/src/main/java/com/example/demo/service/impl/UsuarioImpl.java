package com.example.demo.service.impl;

import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.CUsuario;
import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.service.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioImpl implements IUsuarioService{

	private final PasswordEncoder pass;
	private final IUsuarioRepository iUsuarioRepository; 
	
	@Override
	public Map<String, String> onChangePassword(String email,String password,String updatePassword) {
		
		CUsuario usuarioE= iUsuarioRepository.findByEmail(email).orElseThrow(()-> new EntityNotFoundException("El Usuario "+email+" No Existe"));
		System.out.println(pass.encode("enrike123"));
		if(pass.matches(password, usuarioE.getPassword())) {
			String encodePass= pass.encode(updatePassword);
			usuarioE.setPassword(encodePass);
			usuarioE.setConfirmPassword(encodePass);
			iUsuarioRepository.save(usuarioE);
			return Map.of("message","Contraseña Actualizada");
		}
		throw new BadCredentialsException("Correo o Contraseña Incorrectas");
	}

	
}
