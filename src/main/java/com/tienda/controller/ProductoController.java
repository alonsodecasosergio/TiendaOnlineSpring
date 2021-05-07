package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private CategoriaService cs;
	
	@PostMapping("/alta")
	public String alta() {
		
		return "";
	}
	
	@PostMapping("/baja")
	public String baja() {
		
		return "";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("categorias", cs.getAll());
		model.addAttribute("productos", ps.getAll());
		
		return "productos/list";
	}
}
