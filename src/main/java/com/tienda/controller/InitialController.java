package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.service.ProductoService;

@Controller
@RequestMapping("")
public class InitialController {
	
	@Autowired
	private ProductoService ps;
	
	@GetMapping("")
	public String alta(Model model) {
		
		model.addAttribute("productos", ps.getAll());
				
		return "index";
	}
}
