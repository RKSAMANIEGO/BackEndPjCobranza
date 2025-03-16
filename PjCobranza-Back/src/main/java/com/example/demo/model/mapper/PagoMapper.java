package com.example.demo.model.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.model.CPago;
import com.example.demo.model.dto.PagoRequestDto;

@Mapper(componentModel = "spring")
public interface PagoMapper {

	@Mapping(target="pagoId" , ignore=true)
	@Mapping(target="fechaPago", ignore=true)
	CPago pagoDtoToPago(PagoRequestDto pago, LocalDate fechaActual);
}
