package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.ListPrestamosDto;
import com.example.demo.model.dto.PrestamoRequestDto;
import com.example.demo.service.IPrestamoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/prestamo")
@RequiredArgsConstructor
public class PrestamoController {

	private final IPrestamoService prestamoService;

	@GetMapping
	ResponseEntity<List<ListPrestamosDto>> listarPrestamos(){
		return new ResponseEntity<>(prestamoService.listPrestamos(),HttpStatus.OK);
	}
	
	@PostMapping
	ResponseEntity<?> agregarPrestamo(@RequestBody PrestamoRequestDto prestamo) throws Exception{
		return new ResponseEntity<>(prestamoService.agregarPrestamo(prestamo),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<?>actualizarPrestamo(@PathVariable(name="id")Integer cod, @RequestBody PrestamoRequestDto prestamo){
		return new ResponseEntity<>(prestamoService.actualizarPrestamo(cod, prestamo), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?>eliminarPrestamo(@PathVariable(name="id") Integer cod){
		return new ResponseEntity<>(prestamoService.eliminarPrestamo(cod),HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	ResponseEntity<?> cambiarEstadoPagado(@PathVariable(name="id") Integer cod){
		return new ResponseEntity<>(prestamoService.estadoPagado(cod),HttpStatus.OK);
				
	}
}
