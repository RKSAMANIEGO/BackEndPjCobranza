package com.example.demo.model.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.model.CPrestamo;
import com.example.demo.model.dto.PrestamoRequestDto;


@Mapper(componentModel = "spring")
public interface PrestamoMapper {

	@Mapping(target="prestamoId" , ignore = true)
	@Mapping(target="fechaInicio", expression  = "java(fechaInicio)")
	@Mapping(target="fechaVencimiento", expression  = "java(fechaVencimiento)")
	CPrestamo prestamoDtoToPrestamo(PrestamoRequestDto prestamo,LocalDate fechaInicio,LocalDate fechaVencimiento);
    default java.sql.Date convertToSqlDate(LocalDate localDate) {
        return (localDate == null ? null : java.sql.Date.valueOf(localDate));
    }
}
