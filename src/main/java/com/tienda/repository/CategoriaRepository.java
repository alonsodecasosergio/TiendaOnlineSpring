package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Entities.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	Categoria findById(int id);
}
