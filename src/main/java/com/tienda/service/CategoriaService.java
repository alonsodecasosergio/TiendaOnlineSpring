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
	
	public Categoria getCategoriaById(int id) {
		
		return repository.findById(id);
	}
	
	public void addCategoria(Categoria categoria) {
		repository.save(categoria);
	}
	
	public void deleteCategoria(int id) {
		repository.deleteById(id);
	}

}
