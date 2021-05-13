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

/**
 * 
 * @author Sergio
 *
 */
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
	
	/**
	 * Nuevo producto
	 * @param model
	 * @return
	 */
	@GetMapping("/new")
	public String add(Model model) {
		
		model.addAttribute("producto", new Producto());
		model.addAttribute("categorias", cs.getAll());
		
		return "productos/new";
	}
	
	/**
	 * Añadido del nuevo producto
	 * @param model
	 * @param producto
	 * @param file
	 * @return
	 */
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
	
	/**
	 * Lista de productos
	 * @param model
	 * @return
	 */
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("categorias", cs.getAll());
		model.addAttribute("productos", ps.getAll());
		
		return "productos/list";
	}
	
	/**
	 * Detalles d eun producto
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/details/{id}")
	public String details(Model model,@PathVariable("id") int id){
		
		Producto produc = ps.getProducto(id);
		
		model.addAttribute("usuarios", us.getAll());
		model.addAttribute("valoraciones", vs.getByIdProducto(id));
		model.addAttribute("categorias", cs.getAll());
		model.addAttribute("producto", produc);
		
		return "productos/details";
	}
	
	/**
	 * Lista de productos segun la categoria
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/category/{id}")
	public String category(Model model,@PathVariable("id") int id){
		
		model.addAttribute("categorias", cs.getAll());
		model.addAttribute("productos", ps.getAllCategory(id));
				
		return "index";
	}
	
	/**
	 * Lista de productos segun su precio maximo
	 * @param model
	 * @param precio
	 * @return
	 */
	@GetMapping("/prince")
	public String prince(Model model,  @RequestParam double precio) {
		
		model.addAttribute("productos", ps.getProductosPrecioMax(precio));
		System.out.println(ps.getProductosPrecioMax(precio));
		
		return "index";
	}
	
	/**
	 * Borrado de un producto
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/del/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		ps.deleteProducto(id);
		
		return "redirect:/producto/listar";
	}
	
	/**
	 * Editado de un productos
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String editar(Model model, @PathVariable("id") int id) {
		
		Producto producto = ps.getProducto(id);
		model.addAttribute("producto", producto);
		model.addAttribute("categorias", cs.getAll());
		
		return "productos/new";
	}
	
	/**
	 * Exportar la lista de productos
	 * @param model
	 * @return
	 */
	@GetMapping("/export")
	public String exportar(Model model) {
		
		ps.exportarProductos((ArrayList<Producto>) ps.getAll());
		
		return "redirect:/producto/listar";
	}
	
	/**
	 * Importar productos
	 * @param model
	 * @return
	 */
	@GetMapping("/import")
	public String importar(Model model) {
		
		ps.importarProductos();
		
		return "redirect:/producto/listar";
	}
}
