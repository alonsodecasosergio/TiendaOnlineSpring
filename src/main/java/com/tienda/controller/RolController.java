package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.service.RolService;

@Controller
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	private RolService rs;
	
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
		
		model.addAttribute("roles", rs.getAll());
		
		return "roles/list";
	}
}
