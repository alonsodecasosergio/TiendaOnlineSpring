package com.tienda.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Producto;

@Service
public class CarritoService {

	
	public double total(ArrayList<Producto> productos) {
		
		double total = 0; 
		
		for(int i = 0; i < productos.size(); i++) {
			
			Producto producto = productos.get(i);
			
			total += producto.getPrecio();
		}
		
		return total;
	}
}
