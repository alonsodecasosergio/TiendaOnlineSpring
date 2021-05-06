package com.tienda.repository;

import org.springframework.data.repository.CrudRepository;

import com.tienda.model.Entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

}
