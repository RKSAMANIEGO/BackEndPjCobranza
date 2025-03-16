package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CUsuario;

public interface IUsuarioRepository extends JpaRepository<CUsuario, Integer> {

	Optional<CUsuario> findByEmail(String email);
}
