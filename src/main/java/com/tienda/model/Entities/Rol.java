package com.tienda.model.Entities;

public class Rol implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String rol;

	public Rol() {
	}
	
	public Rol(String rol) {
		this.rol = rol;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}
