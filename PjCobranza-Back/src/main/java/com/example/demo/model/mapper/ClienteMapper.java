package com.example.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.model.CCliente;
import com.example.demo.model.dto.ClienteRequestDto;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

	@Mapping(target = "clienteId", ignore = true)
	@Mapping(target = "fechaRegistro", ignore=true )
	CCliente clienteRequestToCliente(ClienteRequestDto clienteReq);
}
