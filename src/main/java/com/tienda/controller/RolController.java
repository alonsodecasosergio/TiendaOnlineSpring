package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.Entities.Categoria;
import com.tienda.model.Entities.Rol;
import com.tienda.service.RolService;

@Controller
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	private RolService rs;
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("roles", rs.getById(id));
		
		return "roles/new";
	}
	
	@GetMapping("/del/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		rs.deleteRol(id);
		
		return "redirect:/rol/listar";
	}
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		
		model.addAttribute("roles", new Rol());
		
		return "roles/new";
	}
	
	@PostMapping("/edit/submit")
	public String validar(Model model, @ModelAttribute Rol rol) {
		
		System.out.println(rol.toString());
		
		rs.addRol(rol);
		
		return "redirect:/rol/listar";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("roles", rs.getAll());
		
		return "roles/list";
	}
}
