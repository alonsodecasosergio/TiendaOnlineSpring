package com.tienda.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.Entities.Categoria;
import com.tienda.model.Entities.Usuario;
import com.tienda.service.RolService;
import com.tienda.service.UsuarioService;
import com.tienda.service.UtilService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService us;
	@Autowired
	private RolService rs;
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("roles", rs.getAll());
		model.addAttribute("usuario", us.getUsuario(id));
		
		return "usuarios/new";
	}
	
	@GetMapping("/del/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		us.deleteById(id);
		
		return "redirect:/usuario/listar";
	}
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		
		model.addAttribute("roles", rs.getAll());
		model.addAttribute("usuario", new Usuario());
		
		return "usuarios/new";
	}
	
	@PostMapping("/edit/submit")
	public String validar(Model model, @Valid @ModelAttribute Usuario usuario,  BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("roles", rs.getAll());
			
			return "usuarios/new";
		}
		else {
			
			us.updateUsuario(usuario);
			
			return "redirect:/usuario/listar";
		}
	}
	
	@PostMapping("/new/submit")
	public String validarNuevo(Model model, @Valid @ModelAttribute Usuario usuario,  BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("roles", rs.getAll());
			
			return "usuarios/new";
		}
		else {
			
			us.addUsuario(usuario);
			
			return "redirect:/usuario/listar";
		}
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
		Usuario userPerfil = us.getUsuario(user.getId());
		userPerfil.setClave(UtilService.desencryptedPassword(userPerfil.getClave()));
		model.addAttribute("usuario", userPerfil);
		
		return "usuarios/perfil";
	}
	
	@PostMapping("/perfil/submit")
	public String guardarPerfil(Model model, @Valid @ModelAttribute Usuario usuario,  BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			return "usuarios/perfil";
		}
		else {
			
			us.addUsuario(usuario);
			
			return "redirect:/usuario/perfil";
		}
	}
}
