package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.model.Entities.Usuario;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@GetMapping("acceso")
	public String acceso() {
		
		return "login/login";
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
		
	
	
	@PostMapping("validaracceso")
	public String validar(@RequestParam String login, @RequestParam String password) {
		
		return "index";
	}
}
