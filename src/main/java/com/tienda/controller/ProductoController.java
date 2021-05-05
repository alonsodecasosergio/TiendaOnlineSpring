package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("producto")
public class ProductoController {
	
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
