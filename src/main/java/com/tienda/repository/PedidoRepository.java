package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Entities.Pedido;

public interface PedidoRepository  extends JpaRepository<Pedido, Integer>{
	Pedido findById(int id);
	Iterable<Pedido> findByIdUsuario(int id);
	Iterable<Pedido> findByEstado(String estado);
	
}
