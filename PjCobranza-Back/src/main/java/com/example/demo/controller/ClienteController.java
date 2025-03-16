package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CCliente;
import com.example.demo.model.dto.ClienteRequestDto;
import com.example.demo.service.IClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

	private final IClienteService clienteService;
	
	@GetMapping
	ResponseEntity<List<CCliente>> listarClientes(){
		return new ResponseEntity<>(clienteService.listarCliente(),HttpStatus.OK);
	}
	
	@PostMapping
	ResponseEntity<?> agregarCliente(@Valid @RequestBody ClienteRequestDto cliente){
		return new ResponseEntity<>(clienteService.agregarCliente(cliente),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<?> actualizarCliente(@PathVariable(name="id")Integer cod ,@Valid @RequestBody ClienteRequestDto cliente){
		return new ResponseEntity<>(clienteService.actualizarCliente(cod, cliente),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?>eliminarCliente(@PathVariable(name="id")Integer cod){
		return new ResponseEntity<>(clienteService.eliminarCliente(cod),HttpStatus.OK);
	}
	
	
	
}
