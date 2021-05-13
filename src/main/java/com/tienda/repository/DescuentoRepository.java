package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Entities.Descuento;


public interface DescuentoRepository extends JpaRepository<Descuento, Integer>{
	Descuento findById(int id);
}
