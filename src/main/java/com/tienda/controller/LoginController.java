package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.model.Entities.Usuario;
import com.tienda.service.UsuarioService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UsuarioService us;
	
	@GetMapping("/acceso")
	public String acceso() {
		
		return "login/login";
	}
	
	@PostMapping("/acceso/validar")
	public String validarAcceso(Model model, @RequestParam(required = true) String login, @RequestParam(required = true) String password) {
		
		Usuario user = us.validarLogin(login, password);
		
		if(user != null) {
			
			return "redirect:/";
			
		}else {
			
			return "login/login";
		}
		
		
		
	}
	
	@GetMapping("registrar")
	public String registrar(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		
		return "login/registrar";
	}
	
	@PostMapping("registrar/validar")
	public String registrarValidar(Model model, @ModelAttribute Usuario usuario) {
		
		usuario.setRol(3);
		System.out.println(usuario.toString());
		
		return "login/login";
		
	}
}
