package com.tienda.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Producto;
import com.tienda.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository repository;
	
	public Iterable<Producto> getAll() {
        return repository.findAll();
    }
	
	public Producto getProducto(int id) {
		
		return repository.findById(id);
	}
	
	public Iterable<Producto> getAllCategory(int id){
		
		return repository.findByIdCategoria(id);
	}
	
	public void deleteProducto(int id) {
		
		repository.deleteById(id);
	}
	
	public void addProducto(Producto producto) {
		
		repository.save(producto);
	}
}
