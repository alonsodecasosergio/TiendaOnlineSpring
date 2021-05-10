package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Entities.DetallePedido;


public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{
	Iterable<DetallePedido> findByIdPedido(int id);
}
