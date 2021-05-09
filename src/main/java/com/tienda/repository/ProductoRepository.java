package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Entities.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	Producto findById(int id);
	Iterable<Producto> findByIdCategoria(int id);
}
