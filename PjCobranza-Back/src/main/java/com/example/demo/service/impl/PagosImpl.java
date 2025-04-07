package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.example.demo.model.CPago;
import com.example.demo.model.dto.ListPagosDto;
import com.example.demo.model.dto.PagoRequestDto;
//import com.example.demo.model.mapper.PagoMapper;
import com.example.demo.repository.IPagosRepository;
import com.example.demo.service.IPagoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagosImpl implements IPagoService{

	private final IPagosRepository pagosRepository;
	//private final PagoMapper pagoMapper;

	@Override
	public List<ListPagosDto> listaPagosByPrestamo(Integer codPrestamo) {
		return pagosRepository.listPagosByCodPrestamo(codPrestamo);
	}
	
	@Override
	public CPago findByIdPago(Integer id) {
		CPago pagoN= pagosRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Pago con ID "+id+" No Existe¡"));
		return pagoN;
	}

	@Override
	public Map<String, String> agregarPago(PagoRequestDto pago) {
		
		if(pago.getPrestamo() == null) {
		 throw new IllegalArgumentException("Ingrese el N° de Prestamo para realizar el Pago.");
		}
	    //LocalDate fechaActual = LocalDate.now();
		//CPago pagoNuevo = pagoMapper.pagoDtoToPago(pago, fechaActual);
	    //CPago pagoNuevo = pagoMapper.pagoDtoToPago(pago);
	    
	    //CONVERSION DE DTO A PAGO 
	    CPago pagoNuevo = CPago.builder()
	    		.montoPago(pago.getMontoPago())
	    		.metodoPago(pago.getMetodoPago())
	    		.prestamo(pago.getPrestamo())
	    		.build();
	    
		pagosRepository.save(pagoNuevo);
		return Map.of("message","Pago Realizado");	
	}

	@Override
	public Map<String, String> actualizarPago(Integer id, PagoRequestDto pago) {
		
		CPago pagoEncontrado = pagosRepository.findById(id).orElseThrow( ()-> new EntityNotFoundException("El Pago con ID "+id+" No Existe."));
		
		pagoEncontrado.setMontoPago(pago.getMontoPago()!=null ? pago.getMontoPago() : pagoEncontrado.getMontoPago());
		pagoEncontrado.setMetodoPago(pago.getMetodoPago()!=null ? pago.getMetodoPago() : pagoEncontrado.getMetodoPago());
		
		pagosRepository.save(pagoEncontrado);
		return Map.of("message","Pago Actualizado");
	}

	@Override
	public Map<String, String> eliminarPago(Integer id) {
		CPago pagoEncontrado = pagosRepository.findById(id).orElseThrow( ()-> new EntityNotFoundException("El Pago con ID "+id+" No Existe."));
		pagosRepository.delete(pagoEncontrado);
		return Map.of("message","Pago Eliminado");
	}


}
