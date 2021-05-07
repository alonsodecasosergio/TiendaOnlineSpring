package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Categoria;
import com.tienda.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	
	public Iterable<Categoria> getAll(){
		
		return repository.findAll();
		
		
	}
	

}
