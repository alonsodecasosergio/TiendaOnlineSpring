package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.OpcionMenu;
import com.tienda.repository.OpcionesMenuRepository;

@Service
public class OpcionMenuService {
	
	@Autowired
	private OpcionesMenuRepository omr;
	
	public Iterable<OpcionMenu> getAll(){
		return omr.findAll();
	}
	
	public Iterable<OpcionMenu> getByIdRol(int id){
		
		return omr.findByIdRol(id);
	}
}
