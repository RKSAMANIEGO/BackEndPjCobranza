package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.CUsuario;
import com.example.demo.repository.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CUserDetailsServiceImpl implements UserDetailsService{

	private final IUsuarioRepository usuarioRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		CUsuario usuarioE= usuarioRepository.findByEmail(username).orElseThrow( ()-> new UsernameNotFoundException("Usuario No Encontrado"));
		
		List<SimpleGrantedAuthority> authority = new ArrayList<>();
	
		authority.add(new SimpleGrantedAuthority("ROLE_"+usuarioE.getRol()));
		
		return new User(usuarioE.getEmail(), usuarioE.getPassword(), true, true, true, true, authority);
		
	}

}

