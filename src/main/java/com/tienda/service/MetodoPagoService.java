package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.MetodoPago;
import com.tienda.repository.MetodoPagoRepository;

@Service
public class MetodoPagoService {

	@Autowired
	private MetodoPagoRepository repository;
	
	public Iterable<MetodoPago> getAll(){
		
		return repository.findAll();
	}
}
