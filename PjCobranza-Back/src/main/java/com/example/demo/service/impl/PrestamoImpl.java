package com.example.demo.service.impl;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.CPrestamo;
import com.example.demo.model.dto.ListPrestamosDto;
import com.example.demo.model.dto.PrestamoRequestDto;
import com.example.demo.model.enumerated.EnumEstadoPrestamos;
import com.example.demo.model.mapper.PrestamoMapper;
import com.example.demo.repository.IPrestamoRepository;
import com.example.demo.service.IPrestamoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoImpl implements IPrestamoService {

	private final IPrestamoRepository iPrestamoRepository;
	private final PrestamoMapper prestamoMapper;
	
	@Override
	public List<ListPrestamosDto> listPrestamos() {	
		return iPrestamoRepository.listPrestamos();
	}

	@Override
	public CPrestamo agregarPrestamo(PrestamoRequestDto prestamo) throws Exception {
		
		CPrestamo prestamoNuevo  = new CPrestamo();
		try {
			LocalDate fechaInicio= LocalDate.now();
			LocalDate fechaVencimiento= fechaInicio.plusDays(prestamo.getTiempo());
			String estado = EnumEstadoPrestamos.pendiente.toString();
			prestamoNuevo =prestamoMapper.prestamoDtoToPrestamo(prestamo, fechaInicio, fechaVencimiento, estado);
		} catch (Exception e) {
			throw new Exception("Error "+e.getMessage());
		}
		return iPrestamoRepository.save(prestamoNuevo);
	}

	@Override
	public CPrestamo actualizarPrestamo(Integer id, PrestamoRequestDto prestamo) {
		
		CPrestamo prestamoE= iPrestamoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("El Prestamo con ID "+id+" No Existe."));
		
		prestamoE.setMonto(prestamo.getMonto() != null ? prestamo.getMonto() : prestamoE.getMonto());
		
		if(prestamo.getTiempo() !=null) {
			int tiempo = prestamo.getTiempo();
			LocalDate fechaI=prestamoE.getFechaInicio();
			LocalDate fechaV=fechaI.plusDays(tiempo);
			
			prestamoE.setTiempo(tiempo);
			prestamoE.setFechaVencimiento(fechaV);
		}
		prestamoE.setTasaInteres(prestamo.getTasaInteres()!=null ? prestamo.getTasaInteres() : prestamoE.getTasaInteres());
	
		return iPrestamoRepository.save(prestamoE);
	}

	@Override
	public Map<String, String> eliminarPrestamo(Integer id) {
		CPrestamo prestamoE= iPrestamoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("El Prestamo con ID "+id+" No Existe."));
		iPrestamoRepository.delete(prestamoE);
		return Map.of("message", "Prestamo de "+prestamoE.getCliente().getNombre()+" "+prestamoE.getCliente().getApellido()+" Eliminado Correctamente." );
	}

	@Override
	public Map<String, String> estadoPagado(Integer id) {
		CPrestamo prestamoE= iPrestamoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("El Prestamo con ID "+id+" No Existe."));
		prestamoE.setEstado(EnumEstadoPrestamos.pagado);
		iPrestamoRepository.save(prestamoE);
		return Map.of("message","El Prestado de "+prestamoE.getCliente().getNombre()+" "+prestamoE.getCliente().getApellido()+" Se Encuentra Pagado.");
	}


}
