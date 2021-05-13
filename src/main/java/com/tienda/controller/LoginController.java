package com.tienda.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.model.Entities.Producto;
import com.tienda.model.Entities.Usuario;
import com.tienda.service.OpcionMenuService;
import com.tienda.service.UsuarioService;
import com.tienda.service.UtilService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UsuarioService us;
	
	@Autowired
	private OpcionMenuService oms;
	
	@GetMapping("/acceso")
	public String acceso() {
		
		return "login/login";
	}
	
	@PostMapping("/acceso/validar")
	public String validarAcceso(HttpSession sesion, Model model, @RequestParam(required = true) String login, @RequestParam(required = true) String password) {
		
		Usuario user = us.validarLogin(login, password);
		sesion.setAttribute("usuario", user);
		
		if(user != null) {
			
			sesion.setAttribute("opcionesMenu", oms.getByIdRol(user.getRol()));
			
			return "redirect:/";
			
		}else {
			
			return "redirect:/login/acceso";
		}
		
		
		
	}
	
	@GetMapping("registrar")
	public String registrar(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		try {
			model.addAttribute("provincias", UtilService.getAllProvincias());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "login/registrar";
	}
	
	@PostMapping("registrar/validar")
	public String registrarValidar(Model model,@Valid @ModelAttribute Usuario usuario,  BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			try {
				model.addAttribute("provincias", UtilService.getAllProvincias());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "login/registrar";
		}
		else {
			
			if(us.getUsuario(usuario.getEmail()) == null) {
				usuario.setRol(3);
				us.addUsuario(usuario);
				
				return "redirect:/login/acceso";
				
			}else {
				
				model.addAttribute("emailIncorrecto", "El email ya esta en uso");
				try {
					model.addAttribute("provincias", UtilService.getAllProvincias());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "login/registrar";
			}
		}
		
	}
	
	@GetMapping("/close")
	public String cerrarSesion(HttpSession sesion, Model model) {
		
		sesion.invalidate();		
		
		return "redirect:/";
		
	}
}
