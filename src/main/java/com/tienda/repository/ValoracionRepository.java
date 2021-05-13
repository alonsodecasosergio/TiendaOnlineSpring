package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Entities.Valoracion;

public interface ValoracionRepository  extends JpaRepository<Valoracion, Integer>{
	Iterable<Valoracion> findByIdProducto(int id);
	Iterable<Valoracion> findByValoracion(int id);
}
