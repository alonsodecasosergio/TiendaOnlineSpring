package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
	Proveedor findById(int id);
}
