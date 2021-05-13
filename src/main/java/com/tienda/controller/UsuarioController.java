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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.Entities.Categoria;
import com.tienda.model.Entities.Pedido;
import com.tienda.model.Entities.Producto;
import com.tienda.model.Entities.Usuario;
import com.tienda.model.Entities.Valoracion;
import com.tienda.service.PedidoService;
import com.tienda.service.ProductoService;
import com.tienda.service.RolService;
import com.tienda.service.UsuarioService;
import com.tienda.service.UtilService;
import com.tienda.service.ValoracionService;

/**
 * 
 * @author Sergio
 *
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService us;
	@Autowired
	private RolService rs;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ValoracionService valoracionService;
	@Autowired
	private ProductoService productoService;
	
	/**
	 * Editado de un usuario
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("roles", rs.getAll());
		model.addAttribute("usuario", us.getUsuario(id));
		
		return "usuarios/new";
	}
	
	/**
	 * Borrado de un usuario
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/del/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		
		us.deleteById(id);
		
		return "redirect:/usuario/listar";
	}
	
	/**
	 * Añadido de un usuario
	 * @param model
	 * @return
	 */
	@GetMapping("/new")
	public String nuevo(Model model) {
		
		model.addAttribute("roles", rs.getAll());
		model.addAttribute("usuario", new Usuario());
		
		return "usuarios/new";
	}
	
	/**
	 * Validar la edicion de un usuario
	 * @param model
	 * @param usuario
	 * @param bindingResult
	 * @return
	 */
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
	
	/**
	 * Validar el añadido de un nuevo usuario
	 * @param model
	 * @param usuario
	 * @param bindingResult
	 * @return
	 */
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
	
	/**
	 * Listar los usuarios
	 * @param model
	 * @return
	 */
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("roles", rs.getAll());
		model.addAttribute("usuarios", us.getAll());
		
		return "usuarios/list";
	}
	
	/**
	 * Perfil de un usuario
	 * @param sesion
	 * @param model
	 * @return
	 */
	@GetMapping("/perfil")
	public String perfil(HttpSession sesion,Model model) {
		
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		Usuario userPerfil = us.getUsuario(user.getId());
		userPerfil.setClave(UtilService.desencryptedPassword(userPerfil.getClave()));
		model.addAttribute("usuario", userPerfil);
		
		return "usuarios/perfil";
	}
	
	/**
	 * Validacion de los cambios del perfil de usuario
	 * @param model
	 * @param usuario
	 * @param bindingResult
	 * @return
	 */
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
	
	/**
	 * Estadisticas para los administradores
	 * @param model
	 * @return
	 */
	@GetMapping("/stats")
	public String estadisticas(Model model) {
		
		ArrayList<Pedido> enviados = (ArrayList<Pedido>) pedidoService.getPedidoByEstado("Enviado"); 
		
		model.addAttribute("pedidosEnviados", enviados.size());
		
		ArrayList<Pedido> pendientes = (ArrayList<Pedido>) pedidoService.getPedidoByEstado("Pendiente"); 
		
		model.addAttribute("pedidosPendiente", pendientes.size());
		
		ArrayList<Pedido> cancelados = (ArrayList<Pedido>) pedidoService.getPedidoByEstado("Cancelado"); 
		
		model.addAttribute("pedidosCancelados", cancelados.size());
		
		ArrayList<Pedido> porCancelar = (ArrayList<Pedido>) pedidoService.getPedidoByEstado("Pendiente Cancelar"); 
		
		model.addAttribute("pedidosPorCancelar", porCancelar.size());
		
		ArrayList<Valoracion> valoraciones = (ArrayList<Valoracion>) valoracionService.getByValoracion(5);
		
		ArrayList<Producto> productosMejorValorados = new ArrayList<Producto>();
		
		for(int i = 0; i < valoraciones.size(); i++) {
			
			Producto produc = (Producto) productoService.getProducto(valoraciones.get(i).getIdProducto());
			if(!productosMejorValorados.contains(produc)) {

				productosMejorValorados.add(produc);
			}
		}
		
		model.addAttribute("productos", productosMejorValorados);
		
		
		
		return "usuarios/stats";
	}
}
