package com.tienda.model.Entities;

/**
 * 
 * @author Sergio
 *
 */
public class Provincia {
	
	private int id;
	private String nm;
	
	
	public Provincia(int id, String nm) {
		super();
		this.id = id;
		this.nm = nm;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNm() {
		return nm;
	}


	public void setNm(String nm) {
		this.nm = nm;
	}
	
	
	

}
