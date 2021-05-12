package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Entities.Configuracion;

public interface ConfiguracionRepository extends JpaRepository<Configuracion, Integer>{
	Configuracion findByClave(String clave);
	Configuracion findById(int id);
}
