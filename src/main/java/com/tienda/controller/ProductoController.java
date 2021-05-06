package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.service.ProductoService;

@Controller
@RequestMapping("producto")
public class ProductoController {
	
	@Autowired
	private ProductoService ps;
	
	@PostMapping("alta")
	public String alta() {
		
		return "";
	}
	
	@PostMapping("baja")
	public String baja() {
		
		return "";
	}
	
	@PostMapping("listar")
	public String listar() {
		
		return "";
	}
}
