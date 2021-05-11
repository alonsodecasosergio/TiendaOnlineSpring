package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tienda.model.Entities.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	Producto findById(int id);
	Iterable<Producto> findByIdCategoria(int id);
	
	@Query("select p from Producto p where p.precio <= ?1")
	Iterable<Producto> findByPrecioMaximo(double precio);
}
