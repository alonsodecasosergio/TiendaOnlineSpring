package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.Entities.Producto;
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
	
	@GetMapping("/details/{id}")
	public String details(Model model,@PathVariable("id") int id){
		
		Producto produc = ps.getProducto(id);
		
		model.addAttribute("categorias", cs.getAll());
		model.addAttribute("producto", produc);
		
		return "productos/details";
	}
	
	@GetMapping("/category/{id}")
	public String category(Model model,@PathVariable("id") int id){
		
		model.addAttribute("categorias", cs.getAll());
		model.addAttribute("productos", ps.getAllCategory(id));
				
		return "index";
	}
	
	@GetMapping("/del/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		ps.deleteProducto(id);
		
		return "redirect:/producto/listar";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(Model model, @PathVariable("id") int id) {
		
		Producto producto = ps.getProducto(id);
		model.addAttribute("producto", producto);
		
		return "productos/new";
	}
}
