package com.tienda.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tienda.model.Entities.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import com.tienda.service.UsuarioService;
import com.tienda.service.UtilService;
import com.tienda.service.ValoracionService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private CategoriaService cs;
	@Autowired
	private UsuarioService us;
	@Autowired
	private ValoracionService vs;
	
	@GetMapping("/new")
	public String add(Model model) {
		
		model.addAttribute("producto", new Producto());
		model.addAttribute("categorias", cs.getAll());
		
		return "productos/new";
	}
	
	@PostMapping("/new/submit")
	public String addSubmit(Model model, @ModelAttribute Producto producto, @RequestParam("file") MultipartFile file) {
		
		try {
			if(!file.isEmpty()) {
				producto.setImagen(file.getOriginalFilename());
				
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
			    URL appResourceURL = loader.getResource("static");
			    String dbConfigFileRoute = appResourceURL.getPath();
			    dbConfigFileRoute = dbConfigFileRoute.substring(1, dbConfigFileRoute.length());

			    String ruta = dbConfigFileRoute + "/img/" + file.getOriginalFilename();
			    
			    Files.copy(file.getInputStream(), Paths.get(ruta));
			    
			}
			} catch (IOException e) {
				System.out.println(e);
				//throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
			}
		
		ps.addProducto(producto);
		
		return "redirect:/producto/listar";
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
		
		model.addAttribute("usuarios", us.getAll());
		model.addAttribute("valoraciones", vs.getByIdProducto(id));
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
	
	@GetMapping("/prince")
	public String prince(Model model,  @RequestParam double precio) {
		
		model.addAttribute("productos", ps.getProductosPrecioMax(precio));
		System.out.println(ps.getProductosPrecioMax(precio));
		
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
		model.addAttribute("categorias", cs.getAll());
		
		return "productos/new";
	}
	
	@GetMapping("/export")
	public String exportar(Model model) {
		
		ps.exportarProductos((ArrayList<Producto>) ps.getAll());
		
		return "redirect:/producto/listar";
	}
	
	@GetMapping("/import")
	public String importar(Model model) {
		
		ps.importarProductos();
		
		return "redirect:/producto/listar";
	}
}
