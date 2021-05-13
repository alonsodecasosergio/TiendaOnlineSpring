package com.tienda.model.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Sergio
 *
 */
@Entity
@Table(name = "metodos_pago")
public class MetodoPago {
	
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "metodo_pago")
	private String metodoPago;
	
	public MetodoPago() {
		
	}
	
	public MetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	
	
	
	

}
