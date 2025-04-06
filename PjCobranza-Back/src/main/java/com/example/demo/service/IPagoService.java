package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.model.CPago;
import com.example.demo.model.dto.ListPagosDto;
import com.example.demo.model.dto.PagoRequestDto;

public interface IPagoService {

	List<ListPagosDto> listaPagosByPrestamo(Integer codPrestamo);
	CPago findByIdPago(Integer id);
	Map<String,String> agregarPago(PagoRequestDto pago);
	Map<String,String> actualizarPago(Integer id, PagoRequestDto pago);
	Map<String,String> eliminarPago(Integer id);
}
