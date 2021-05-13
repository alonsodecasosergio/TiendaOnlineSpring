package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Descuento;
import com.tienda.repository.DescuentoRepository;

@Service
public class DescuentoService {

	@Autowired
	private DescuentoRepository repository;
	
	
	public Iterable<Descuento> getAll(){
		return repository.findAll();
	}
	
	public Descuento getById(int id) {
		return repository.findById(id);
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
