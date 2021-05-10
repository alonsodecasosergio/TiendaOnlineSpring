package com.tienda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.Entities.Pedido;
import com.tienda.model.Entities.Usuario;
import com.tienda.service.DetallePedidoService;
import com.tienda.service.PedidoService;
import com.tienda.service.ProductoService;
import com.tienda.service.UsuarioService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService ps;
	@Autowired
	private UsuarioService us;
	@Autowired
	private DetallePedidoService dps;
	@Autowired
	private ProductoService producServi;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("usuarios", us.getAll());
		model.addAttribute("pedidos", ps.getAll());
		
		return "pedidos/list";
	}
	
	@GetMapping("/listar/client")
	public String listarClient(HttpSession sesion, Model model) {
		
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		
		model.addAttribute("pedidos", ps.getByIdUsuario(user.getId()));
		
		return "pedidos/listCliente";
	}
	
	@GetMapping("/cancel/{id}")
	public String cancel(Model model,@PathVariable("id") int id) {
		
		Pedido pedido = ps.getPedido(id);
		
		pedido.setEstado("Cancelado");
		
		ps.addPedido(pedido);
		
		return "redirect:/pedido/listar";
	}
	
	@GetMapping("/cancelclient/{id}")
	public String cancelClient(Model model,@PathVariable("id") int id) {
		
		Pedido pedido = ps.getPedido(id);
		
		pedido.setEstado("Pendiente Cancelar");
		
		ps.addPedido(pedido);
		
		return "redirect:/pedido/listar/client";
	}
	
	@GetMapping("/send/{id}")
	public String enviar(Model model,@PathVariable("id") int id) {
		
		Pedido pedido = ps.getPedido(id);
		
		pedido.setEstado("Enviado");
		
		ps.addPedido(pedido);
		
		return "redirect:/pedido/listar";
	}
	
	@GetMapping("/details/{id}")
	public String detalles(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("detalles", dps.getByIdPedido(id));
		model.addAttribute("productos", producServi.getAll());
		
		return "pedidos/listDetails";
	}
}
