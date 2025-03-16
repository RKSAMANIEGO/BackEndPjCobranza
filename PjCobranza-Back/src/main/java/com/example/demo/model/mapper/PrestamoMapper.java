package com.example.demo.model.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.model.CPrestamo;
import com.example.demo.model.dto.PrestamoRequestDto;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {

	@Mapping(target="prestamoId" , ignore = true)
	@Mapping(target="estado" , conditionExpression = "java(estado)")
	@Mapping(target="fechaInicio", conditionExpression = "java(fechaInicio)")
	@Mapping(target="fechaVencimiento", conditionExpression = "java(fechaVencimiento)")
	CPrestamo prestamoDtoToPrestamo(PrestamoRequestDto prestamo,LocalDate fechaInicio,LocalDate fechaVencimiento,String estado);
	
}
