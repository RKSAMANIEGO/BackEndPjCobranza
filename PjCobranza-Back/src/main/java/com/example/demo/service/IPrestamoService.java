package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.model.CPrestamo;
import com.example.demo.model.dto.ListPrestamosDto;
import com.example.demo.model.dto.PrestamoRequestDto;

public interface IPrestamoService {

	List<ListPrestamosDto> listPrestamos();
	CPrestamo agregarPrestamo(PrestamoRequestDto prestamo) throws Exception;
	CPrestamo actualizarPrestamo(Integer id , PrestamoRequestDto prestamo);
	Map<String,String> eliminarPrestamo(Integer id);
	Map<String,String> estadoPagado(Integer id);
}
