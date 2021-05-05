package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rol")
public class RolController {
	
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
