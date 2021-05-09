package com.tienda.model.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "opciones_menu")
public class OpcionMenu {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "id_rol")
	private int idRol;
	
	@Column(name = "nombre_opcion")
	private String nombreOpcion;
	
	@Column(name = "url_opcion")
	private String urlOpcion;
	
	public OpcionMenu() {
		
	}

	public OpcionMenu(int idRol, String nombreOpcion, String urlOpcion) {
		this.idRol = idRol;
		this.nombreOpcion = nombreOpcion;
		this.urlOpcion = urlOpcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombreOpcion() {
		return nombreOpcion;
	}

	public void setNombreOpcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
	}

	public String getUrlOpcion() {
		return urlOpcion;
	}

	public void setUrlOpcion(String urlOpcion) {
		this.urlOpcion = urlOpcion;
	}
	
	

}
