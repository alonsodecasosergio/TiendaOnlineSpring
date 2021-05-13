package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Proveedor;
import com.tienda.repository.ProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository repository;
	
	
	public Iterable<Proveedor> getAll(){
		return repository.findAll();
	}
	
	
	public void add(Proveedor proveedor) {
		repository.save(proveedor);
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
	
	public Proveedor getById(int id) {
		return repository.findById(id);
	}
}
