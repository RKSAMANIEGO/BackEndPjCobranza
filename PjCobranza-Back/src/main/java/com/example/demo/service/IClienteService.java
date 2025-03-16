package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CCliente;
import com.example.demo.model.dto.ClienteRequestDto;


public interface IClienteService {

	List<CCliente> listarCliente();
	
	CCliente agregarCliente(ClienteRequestDto cliente);
	
	CCliente actualizarCliente(Integer id, ClienteRequestDto cliente);
	
	String eliminarCliente(Integer id);
}
