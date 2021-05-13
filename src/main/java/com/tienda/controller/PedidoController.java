package com.tienda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.model.Entities.Configuracion;
import com.tienda.model.Entities.DetallePedido;
import com.tienda.model.Entities.Pedido;
import com.tienda.model.Entities.Usuario;
import com.tienda.model.Entities.Valoracion;
import com.tienda.service.ConfiguracionService;
import com.tienda.service.DetallePedidoService;
import com.tienda.service.PedidoService;
import com.tienda.service.ProductoService;
import com.tienda.service.UsuarioService;
import com.tienda.service.ValoracionService;

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
	@Autowired
	private ValoracionService vs;
	@Autowired
	private ConfiguracionService cs;
	
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
		
		ps.pedidoEnviado(pedido);
		
		return "redirect:/pedido/listar";
	}
	
	@GetMapping("/assess/{id}")
	public String valorar(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("producto", producServi.getProducto(id));
		
		return "productos/valoracion";
	}
	
	@GetMapping("/assess/valorar/{id}")
	public String guardarValoracion(HttpSession sesion, Model model, @PathVariable("id") int id, @RequestParam int puntuacion, @RequestParam String comentario) {
		
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		
		Valoracion valoracion = new Valoracion(id, user.getId() ,puntuacion, comentario);
		
		vs.addValoracion(valoracion);
		
		return "redirect:/";
	}
	
	@GetMapping("/del/detail/{id}")
	public String borrarLinea(Model model, @PathVariable("id") int id) {
		
		DetallePedido dp = (DetallePedido) dps.getDetallePedidoById(id);
		int idPedido = dp.getIdPedido();
		dps.deleteDetallePedido(id, idPedido);
		
		return "redirect:/pedido/details/" + idPedido;
	}
	
	@GetMapping("/details/{id}")
	public String detalles(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("pedido", ps.getPedido(id));
		model.addAttribute("detalles", dps.getByIdPedido(id));
		model.addAttribute("productos", producServi.getAll());
		
		return "pedidos/listDetails";
	}
	
	@GetMapping("/invoice/{id}")
	public String factura(Model model, @PathVariable("id") int id) {
		
		ps.crearFactura(id);
		
		return "redirect:/pedido/details/"+id;
	}
}
