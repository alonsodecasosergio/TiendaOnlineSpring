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
import com.tienda.model.Entities.Configuracion;
import com.tienda.service.CategoriaService;

/**
 * 
 * @author Sergio
 *
 */
@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService cs;
	
	/**
	 * Edicion de la categoria
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("categoria", cs.getCategoriaById(id));
		
		return "categorias/new";
	}
	
	/**
	 * Borrado de una categoria
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/del/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		cs.deleteCategoria(id);
		
		return "redirect:/categoria/listar";
	}
	
	/**
	 * Añadido de una nueva categoria
	 * @param model
	 * @return
	 */
	@GetMapping("/new")
	public String nuevo(Model model) {
		
		model.addAttribute("categoria", new Categoria());
		
		return "categorias/new";
	}
	
	/**
	 * Validacion al añadir una categoria
	 * @param model
	 * @param categoria
	 * @return
	 */
	@PostMapping("/edit/submit")
	public String validar(Model model, @ModelAttribute Categoria categoria) {
		
		cs.addCategoria(categoria);
		
		return "redirect:/categoria/listar";
	}
	
	/**
	 * Listado de las categorias
	 * @param model
	 * @return
	 */
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("categorias", cs.getAll());
		
		return "categorias/list";
	}
}
