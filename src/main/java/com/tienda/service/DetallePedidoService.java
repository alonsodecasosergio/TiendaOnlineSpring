package com.tienda.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.DetallePedido;
import com.tienda.model.Entities.Pedido;
import com.tienda.model.Entities.Producto;
import com.tienda.repository.DetallePedidoRepository;

@Service
public class DetallePedidoService {
	
	@Autowired
	private DetallePedidoRepository repository;
	@Autowired
	private PedidoService pedidoService;
	
	public void addDetallePedido(DetallePedido lineaPedido) {
		
		repository.save(lineaPedido);
	}
	
	
	public Iterable<DetallePedido> getByIdPedido(int id) {
		
		return repository.findByIdPedido(id);
	}
	
	public void deleteDetallePedido(int id, int idPedido) {
		
		repository.deleteById(id);
		calcularTotal(idPedido);
	}
	
	public void calcularTotal(int idPedido) {
		
		Pedido pedido = pedidoService.getPedido(idPedido);
		
		ArrayList<DetallePedido> lineas = (ArrayList<DetallePedido>) getByIdPedido(idPedido);
		
		double total = 0; 
		
		for(int i = 0; i < lineas.size(); i++) {
			
			DetallePedido linea = lineas.get(i);
			
			total += linea.getTotal();
		}
		
		total = Math.round(total * 100) / 100d;
		
		
		pedido.setTotal(total);
		pedidoService.addPedido(pedido);
	}
	
	public DetallePedido getDetallePedidoById(int id) {
		return repository.findById(id);
	}

}
