package com.tienda.model.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedores")
public class Proveedor {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "localidad")
	private String localidad;
	
	@Column(name = "provincia")
	private String provincia;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "cif")
	private String cif;
	
	@Column(name = "email")
	private String email;
	
	public Proveedor() {
		
	}

	public Proveedor(String nombre, String direccion, String localidad, String provincia, String telefono, String cif,
			String email) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.telefono = telefono;
		this.cif = cif;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
