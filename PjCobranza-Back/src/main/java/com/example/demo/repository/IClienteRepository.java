package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CCliente;

public interface IClienteRepository extends JpaRepository<CCliente,Integer> {

	Optional<CCliente> findByDni(String dni);
}
