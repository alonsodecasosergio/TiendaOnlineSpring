package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Configuracion;
import com.tienda.repository.ConfiguracionRepository;

@Service
public class ConfiguracionService {

	@Autowired
	private ConfiguracionRepository repository;
	
	public Iterable<Configuracion> getAll(){
		
		return repository.findAll();
	}
}
