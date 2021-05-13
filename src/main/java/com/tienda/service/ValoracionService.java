package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Valoracion;
import com.tienda.repository.ValoracionRepository;

@Service
public class ValoracionService {

	@Autowired
	private ValoracionRepository repository;
	
	public Iterable<Valoracion> getAll(){
		return repository.findAll();
	}
	
	public void addValoracion(Valoracion valoracion) {
		
		repository.save(valoracion);
	}
	
	public Iterable<Valoracion> getByIdProducto(int id){
		
		return repository.findByIdProducto(id);
	}
	
	public Iterable<Valoracion> getByValoracion(int valoracion){
		
		return repository.findByValoracion(valoracion);
	}
	
	
}
