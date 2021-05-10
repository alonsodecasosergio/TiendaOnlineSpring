package com.tienda.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.DetallePedido;
import com.tienda.model.Entities.Pedido;
import com.tienda.model.Entities.Producto;
import com.tienda.model.Entities.Usuario;
import com.tienda.repository.DetallePedidoRepository;
import com.tienda.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	@Autowired
	private DetallePedidoService serviceDetalle;
	
	
	public void addPedido(Pedido pedido) {
		
		repository.save(pedido);
	}
	
	public Iterable<Pedido> getAll() {
		
		 return repository.findAll();
	}
	
	public void crearPedido(Usuario user, ArrayList<Producto> productos, String pago) {
		
		Pedido pedido = new Pedido();
		
		pedido.setIdUsuario(user.getId());
		pedido.setEstado("Pendiente");
		pedido.setMetodoPago(pago);
		pedido.setNumFactura("0001");
		
		addPedido(pedido);
		
		double total = 0; 
		
		for(int i = 0; i < productos.size(); i++) {
			
			Producto producto = productos.get(i);
			
			DetallePedido linea = new DetallePedido(pedido.getId(),
					producto.getId(), Float.parseFloat(producto.getPrecio().toString()), 1, producto.getImpuesto(), producto.getPrecio());
			
			serviceDetalle.addDetallePedido(linea);
			
			total += producto.getPrecio();
		}
		
		pedido.setTotal(total);
		addPedido(pedido);
		
	}
}
