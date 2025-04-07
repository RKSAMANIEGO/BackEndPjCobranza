package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.CCliente;
import com.example.demo.model.dto.ClienteRequestDto;
//import com.example.demo.model.mapper.ClienteMapper;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.service.IClienteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {
	
	private final IClienteRepository clienteRepository;
	//private final ClienteMapper clienteMapper;
	
	@Override 
	public List<CCliente> listarCliente() {
		return clienteRepository.findAll();
	}

	@Override
	public CCliente findById(Integer id) {
		CCliente clienteN= clienteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Cliente con ID "+id+" No Existe"));
		return clienteN;
	}
	
	@Override
	public CCliente agregarCliente(ClienteRequestDto cliente) {
		
		if(!clienteRepository.findByDni(cliente.getDni()).isPresent()) {		
			//CCliente clienteNuevo = clienteMapper.clienteRequestToCliente(cliente);
			
			//CONVERSION DE DTO A CLIENTE
			CCliente clienteNuevo=CCliente.builder()
					.nombre(cliente.getNombre())
					.apellido(cliente.getApellido())
					.dni(cliente.getDni())
					.correo(cliente.getCorreo())
					.telefono(cliente.getTelefono())
					.direccion(cliente.getDireccion())
					.build();
			
			return clienteRepository.save(clienteNuevo);
		}	
		
		throw new IllegalArgumentException("Ya Existe el Dni del Cliente");
	}

	@Override
	public CCliente actualizarCliente(Integer id, ClienteRequestDto cliente) {
		CCliente clienteEncontrado =clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente con ID "+id+" No Existe."));
		
		clienteEncontrado.setNombre(cliente.getNombre()!=null ? cliente.getNombre() : clienteEncontrado.getNombre());
		clienteEncontrado.setApellido(cliente.getApellido() !=null ? cliente.getApellido() : clienteEncontrado.getApellido());
		clienteEncontrado.setDni(cliente.getDni() != null ? cliente.getDni() : clienteEncontrado.getDni());
		clienteEncontrado.setCorreo(cliente.getCorreo() != null ? cliente.getCorreo() : clienteEncontrado.getCorreo());
		clienteEncontrado.setTelefono(cliente.getTelefono() !=null ? cliente.getTelefono() : clienteEncontrado.getTelefono());
		clienteEncontrado.setDireccion(cliente.getDireccion() != null ? cliente.getDireccion() : clienteEncontrado.getDireccion());
		
		return clienteRepository.save(clienteEncontrado);
	}

	@Override
	public String eliminarCliente(Integer id) {
		CCliente clienteEncontrado =clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente con ID "+id+" No Existe."));
		clienteRepository.delete(clienteEncontrado);
		return "Cliente "+clienteEncontrado.getNombre()+" Eliminado Correctamente";
	}


	
	

}
