package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.DetallePedido;
import com.tienda.repository.DetallePedidoRepository;

@Service
public class DetallePedidoService {
	
	@Autowired
	private DetallePedidoRepository repository;
	
	public void addDetallePedido(DetallePedido lineaPedido) {
		
		repository.save(lineaPedido);
	}
	
	
	public Iterable<DetallePedido> getByIdPedido(int id) {
		
		return repository.findByIdPedido(id);
	}

}
