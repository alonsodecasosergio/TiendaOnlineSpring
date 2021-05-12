package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.service.ConfiguracionService;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {
	
	@Autowired
	private ConfiguracionService cs;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("configuraciones", cs.getAll());
		
		return "configuracion/list";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("configuracion", cs.getConfigById(id));
		
		return "configuracion/new";
	}

}
