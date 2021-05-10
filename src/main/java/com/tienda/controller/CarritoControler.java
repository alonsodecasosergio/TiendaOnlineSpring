package com.tienda.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.model.Entities.Producto;
import com.tienda.model.Entities.Usuario;
import com.tienda.service.CarritoService;
import com.tienda.service.PedidoService;
import com.tienda.service.ProductoService;


@Controller
@RequestMapping("/carrito")
public class CarritoControler {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private PedidoService pedSer;
	@Autowired
	private CarritoService cs;
	
	@GetMapping("/view")
	public String listar(HttpSession sesion, Model model) {
		
		
		model.addAttribute("total", cs.total( (ArrayList<Producto>) sesion.getAttribute("carrito")) + " €");
		
		return "productos/cart";
	}
	
	@GetMapping("/add/{id}")
	public String add(HttpSession sesion, Model model,@PathVariable("id") int id) {
		
		
		ArrayList<Producto> carrito = null;
		
		
		if(sesion.getAttribute("carrito") != null) {
			
			carrito = (ArrayList<Producto>) sesion.getAttribute("carrito");
			
			carrito.add(ps.getProducto(id));
			
		}else {
			
			carrito = new ArrayList<Producto>();
			carrito.add(ps.getProducto(id));
			
			
		}
		sesion.setAttribute("carrito", carrito);
		
		return "redirect:/";
	}
	
	@GetMapping("/del/{id}")
	public String delete(HttpSession sesion, Model model, @PathVariable("id") int id) {
		
		ArrayList<Producto> carrito = (ArrayList<Producto>) sesion.getAttribute("carrito");
		
		
		for(int i = 0; i < carrito.size(); i++) {
			
			Producto produc = carrito.get(i);
			
			if(produc.getId() == id) {
				
				carrito.remove(produc);
				
			}
		}
		
		return "redirect:/carrito/view";
	}
	
	@GetMapping("/order")
	public String pedido(HttpSession sesion, Model model, @RequestParam String pago) {
		
		
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		ArrayList<Producto> carrito = (ArrayList<Producto>) sesion.getAttribute("carrito");
		
		pedSer.crearPedido(user, carrito, pago);
		
		sesion.setAttribute("carrito", new ArrayList<Producto>());
		
		return "pedidos/pagado";
	}
	
	
}
