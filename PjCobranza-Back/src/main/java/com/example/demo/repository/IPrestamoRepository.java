package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.CPrestamo;
import com.example.demo.model.dto.ListPrestamosDto;

public interface IPrestamoRepository extends JpaRepository<CPrestamo, Integer>{

	@Query(nativeQuery = true , value  = "CALL listaGeneralPrestamos()")
	List<ListPrestamosDto> listPrestamos();
	
	@Query(nativeQuery = true , value = "CALL listaPrestamosPorEstado(:estado)")
	List<ListPrestamosDto> listPrestamosByEstado(@Param("estado") String estado);
	
}
