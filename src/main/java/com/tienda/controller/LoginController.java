package com.tienda.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		
		sesion.setAttribute("opcionesMenu", oms.getByIdRol(user.getRol()));
		
		System.out.println(user.toString());
		
		if(user != null) {
			
			ArrayList<Producto> carrito = new ArrayList<Producto>();
			sesion.setAttribute("carrito", carrito);
			
			return "redirect:/";
			
		}else {
			
			return "login/login";
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
	public String registrarValidar(Model model, @ModelAttribute Usuario usuario) {
		
		usuario.setRol(3);
		us.addUsuario(usuario);
		
		return "redirect:/login/acceso";
		
	}
	
	@GetMapping("/close")
	public String cerrarSesion(HttpSession sesion, Model model) {
		
		sesion.invalidate();		
		
		return "redirect:/";
		
	}
}
