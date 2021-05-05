package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@GetMapping("acceso")
	public String acceso() {
		
		return "login/login";
	}
	
	@PostMapping("validaracceso")
	public String validar(@RequestParam String login, @RequestParam String password) {
		
		System.out.println(login + password);
		
		return "index";
	}
}
