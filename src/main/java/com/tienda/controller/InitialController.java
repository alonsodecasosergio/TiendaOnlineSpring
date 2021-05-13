package com.tienda.controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.Entities.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;

/**
 * 
 * @author Sergio
 *
 */
@Controller
@RequestMapping("")
public class InitialController {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private CategoriaService cs;
	
	/**
	 * Inicio de la aplicacion redirigiendo al index
	 * @param sesion
	 * @param model
	 * @return
	 */
	@GetMapping("")
	public String alta(HttpSession sesion, Model model) {
		
		
		sesion.setAttribute("categorias", cs.getAll());
		model.addAttribute("productos", ps.getAll());
				
		return "index";
	}
}
