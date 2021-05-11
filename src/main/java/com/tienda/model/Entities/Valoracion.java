package com.tienda.model.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "valoraciones")
public class Valoracion {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "id_productos")
	private int idProducto;
	
	@Column(name = "id_usuario")
	private int idUsuairo;
	
	@Column(name = "valoracion")
	private int valoracion;
	
	@Column(name = "comentario")
	private String comentario;
	
	public Valoracion() {
		
	}

	public Valoracion(int idProducto, int idUsuairo, int valoracion, String comentario) {
		this.idProducto = idProducto;
		this.idUsuairo = idUsuairo;
		this.valoracion = valoracion;
		this.comentario = comentario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdUsuairo() {
		return idUsuairo;
	}

	public void setIdUsuairo(int idUsuairo) {
		this.idUsuairo = idUsuairo;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
	

}
