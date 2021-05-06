package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findById(int id);
	Usuario findByEmail(String email);
}
