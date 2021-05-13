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
/**
 * 
 * @author Sergio
 *
 */
@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	private ProveedorService ps;
	
	/**
	 * Listar proveedores
	 * @param model
	 * @return
	 */
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("proveedores",ps.getAll());
		
		return "proveedores/list";
	}
	
	/**
	 * Editar un proveedor
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("proveedor", ps.getById(id));
		
		return "proveedores/new";
	}
	
	/**
	 * Borrar un proveedor
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/del/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		ps.deleteById(id);
		
		return "redirect:/proveedor/listar";
	}
	
	/**
	 * Crear un nuevo proveedor
	 * @param model
	 * @return
	 */
	@GetMapping("/new")
	public String nuevo(Model model) {
		
		model.addAttribute("proveedor", new Proveedor());
		
		return "proveedores/new";
	}
	
	/**
	 * Validar el cambio o añadido de proveedor
	 * @param model
	 * @param proveedor
	 * @return
	 */
	@PostMapping("/edit/submit")
	public String validar(Model model, @ModelAttribute Proveedor proveedor) {
		
		ps.add(proveedor);
		
		return "redirect:/proveedor/listar";
	}

}
