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

import com.example.demo.model.CPago;
import com.example.demo.model.dto.ListPagosDto;
import com.example.demo.model.dto.PagoRequestDto;
import com.example.demo.service.IPagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

	private final IPagoService pagoService;

	@GetMapping("/{idPrestamo}")
	ResponseEntity<List<ListPagosDto>> listarPagosPorPrestamos(@PathVariable("idPrestamo") Integer codPrestamo ){
		return new ResponseEntity<>(pagoService.listaPagosByPrestamo(codPrestamo), HttpStatus.OK);
	}
	
	@GetMapping("/buscarPorId/{idPago}")
	ResponseEntity<CPago> buscarPagoPorId(@PathVariable("idPago") Integer codPago){
		return new ResponseEntity<>(pagoService.findByIdPago(codPago), HttpStatus.OK);
	}
	
	@PostMapping
	ResponseEntity<?> realizarPago(@RequestBody PagoRequestDto pago ){
		return new ResponseEntity<>(pagoService.agregarPago(pago),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<?> actualizarPago(@PathVariable("id")Integer cod, @RequestBody PagoRequestDto pago){
		return new ResponseEntity<>(pagoService.actualizarPago(cod, pago),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> eliminarPago(@PathVariable("id")Integer cod){
		return new ResponseEntity<>(pagoService.eliminarPago(cod),HttpStatus.OK);
	}
	
}
