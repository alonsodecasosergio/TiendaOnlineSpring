package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.service.PedidoService;
import com.tienda.service.UsuarioService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService ps;
	@Autowired
	private UsuarioService us;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("usuarios", us.getAll());
		model.addAttribute("pedidos", ps.getAll());
		
		return "pedidos/list";
	}

}
