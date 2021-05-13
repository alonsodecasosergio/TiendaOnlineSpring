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
import com.tienda.model.Entities.Proveedor;
import com.tienda.service.ProveedorService;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	private ProveedorService ps;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("proveedores",ps.getAll());
		
		return "proveedores/list";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("proveedor", ps.getById(id));
		
		return "proveedores/new";
	}
	
	@GetMapping("/del/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		ps.deleteById(id);
		
		return "redirect:/proveedor/listar";
	}
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		
		model.addAttribute("proveedor", new Proveedor());
		
		return "proveedores/new";
	}
	
	@PostMapping("/edit/submit")
	public String validar(Model model, @ModelAttribute Proveedor proveedor) {
		
		ps.add(proveedor);
		
		return "redirect:/proveedor/listar";
	}

}
