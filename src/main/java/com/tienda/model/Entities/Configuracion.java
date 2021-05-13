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
@Table(name = "configuracion")
public class Configuracion {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "clave")
	private String clave;
	
	@Column(name = "valor")
	private String valor;
	
	@Column(name = "tipo")
	private String tipo;
	
	public Configuracion() {
		
	}

	public Configuracion(String clave, String valor, String tipo) {
		this.clave = clave;
		this.valor = valor;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
