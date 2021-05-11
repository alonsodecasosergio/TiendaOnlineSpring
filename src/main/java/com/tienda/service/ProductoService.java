package com.tienda.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JFileChooser;

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
	
	public Iterable<Producto> getProductosPrecioMax(double precio){
		
		return repository.findByPrecioMaximo(precio);
	}
	
	public void exportarProductos(ArrayList<Producto> productos) {
		
		UtilService.exportarProductos(productos);
	}
	
	public void importarProductos() {
		
		File f = new File("C:\\Users\\Formacion\\Desktop\\ficheros\\listadoProductosImportar.xls");
		
		repository.saveAll(UtilService.importarProductos(f));
	}
}
