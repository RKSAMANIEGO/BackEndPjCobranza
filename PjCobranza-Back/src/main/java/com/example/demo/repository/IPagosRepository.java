package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.CPago;
import com.example.demo.model.dto.ListPagosDto;

public interface IPagosRepository extends JpaRepository<CPago, Integer>{

	@Query(value = "CALL listPagosByIdPrestamo(:idPrestamo);" , nativeQuery = true)
	List<ListPagosDto> listPagosByCodPrestamo(@Param("idPrestamo")Integer prestamoId);
}
