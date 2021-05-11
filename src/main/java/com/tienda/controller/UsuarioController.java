package com.tienda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.Entities.Usuario;
import com.tienda.service.RolService;
import com.tienda.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService us;
	@Autowired
	private RolService rs;
	
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
		
		model.addAttribute("roles", rs.getAll());
		model.addAttribute("usuarios", us.getAll());
		
		return "usuarios/list";
	}
	
	@GetMapping("/perfil")
	public String perfil(HttpSession sesion,Model model) {
		
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("usuario", user);
		
		return "usuarios/perfil";
	}
	
	@PostMapping("/perfil/submit")
	public String guardarPerfil(Model model, @ModelAttribute Usuario usuario) {
		
		us.updateUsuario(usuario);
		System.out.println(usuario.toString());
		
		return "redirect:/usuario/perfil";
	}
}
