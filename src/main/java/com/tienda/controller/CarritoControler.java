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

import com.tienda.model.Entities.Descuento;
import com.tienda.model.Entities.Producto;
import com.tienda.model.Entities.Usuario;
import com.tienda.service.CarritoService;
import com.tienda.service.DescuentoService;
import com.tienda.service.MetodoPagoService;
import com.tienda.service.PedidoService;
import com.tienda.service.ProductoService;

/**
 * 
 * @author Sergio
 *
 */
@Controller
@RequestMapping("/carrito")
public class CarritoControler {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private PedidoService pedSer;
	@Autowired
	private CarritoService cs;
	@Autowired
	private MetodoPagoService mps;
	@Autowired
	private DescuentoService ds;
	
	/**
	 * Vista para visualizar la lista del carrito
	 * @param sesion
	 * @param model
	 * @return
	 */
	@GetMapping("/view")
	public String listar(HttpSession sesion, Model model) {
		
		if(sesion.getAttribute("carrito") == null) {

			sesion.setAttribute("carrito",new ArrayList<Producto>());
			
		}
		model.addAttribute("total", cs.total( (ArrayList<Producto>) sesion.getAttribute("carrito")) + " €");
		model.addAttribute("metodoPago", mps.getAll());
		model.addAttribute("descuentos", ds.getAll());
		
		return "productos/cart";
	}
	
	/**
	 * Añadido de un producto al carrito
	 * @param sesion
	 * @param model
	 * @param id
	 * @return
	 */
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
	
	/**
	 * Borrado de un producto del carrito
	 * @param sesion
	 * @param model
	 * @param id
	 * @return
	 */
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
	
	/**
	 * Creacion del pedido a partir del carrito
	 * @param sesion
	 * @param model
	 * @param pago
	 * @param descuento
	 * @param cantidades
	 * @return
	 */
	@GetMapping("/order")
	public String pedido(HttpSession sesion, Model model, @RequestParam String pago, @RequestParam int descuento, @RequestParam int[] cantidades) {
		
		
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		if(user != null) {
			
			ArrayList<Producto> carrito = (ArrayList<Producto>) sesion.getAttribute("carrito");
			Descuento desc = ds.getById(descuento);
			
			pedSer.crearPedido(user, carrito, pago, desc.getDescuento(), cantidades);
			
			sesion.setAttribute("carrito", new ArrayList<Producto>());
			
			return "pedidos/pagado";
		}
		
		return "redirect:/login/acceso";
		
	}
	
	
}
