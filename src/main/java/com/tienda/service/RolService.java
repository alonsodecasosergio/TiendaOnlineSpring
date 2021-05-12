package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Rol;
import com.tienda.repository.RolRepository;

@Service
public class RolService {

	@Autowired
	private RolRepository repository;
	
	public Iterable<Rol> getAll(){
		
		return repository.findAll();
	}
	
	public Rol getById(int id) {
		return repository.findById(id);
	}
	
	public void deleteRol(int id) {
		repository.deleteById(id);
	}
	
	public void addRol(Rol rol) {
		repository.save(rol);
	}
	
}
