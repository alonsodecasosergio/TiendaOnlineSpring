package com.tienda.service;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tienda.model.Entities.Configuracion;
import com.tienda.model.Entities.DetallePedido;
import com.tienda.model.Entities.Pedido;
import com.tienda.model.Entities.Producto;
import com.tienda.model.Entities.Usuario;
import com.tienda.repository.DetallePedidoRepository;
import com.tienda.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	@Autowired
	private DetallePedidoService serviceDetalle;
	@Autowired
	private ProductoService serviceProducto;
	@Autowired
	private ConfiguracionService serviceConfig;
	@Autowired
	private UsuarioService serviceUser;
	
	public void addPedido(Pedido pedido) {
		
		repository.save(pedido);
	}
	
	public Iterable<Pedido> getAll() {
		
		 return repository.findAll();
	}
	
	public Iterable<Pedido> getByIdUsuario(int id) {
		
		 return repository.findByIdUsuario(id);
	}
	
	public void deletePedido(int id) {
		repository.deleteById(id);
	}
	
	public Pedido getPedido(int id) {
		return repository.findById(id);
	}
	
	public Iterable<Pedido> getPedidoByEstado(String estado){
		return repository.findByEstado(estado);
	}
	
	public void crearPedido(Usuario user, ArrayList<Producto> productos, String pago, float descuento, int[] cantidades) {
		
		Pedido pedido = new Pedido();
		
		pedido.setIdUsuario(user.getId());
		pedido.setEstado("Pendiente");
		pedido.setMetodoPago(pago);
		pedido.setNumFactura("####");
		
		addPedido(pedido);
		
		double total = 0; 
		
		for(int i = 0; i < productos.size(); i++) {
			
			Producto producto = productos.get(i);
			
			DetallePedido linea = new DetallePedido(pedido.getId(),
					producto.getId(), Float.parseFloat(producto.getPrecio().toString()), cantidades[i], producto.getImpuesto(), (producto.getPrecio()*cantidades[i]));
			
			
			
			serviceDetalle.addDetallePedido(linea);
			
			total += producto.getPrecio();
		}
		
		descuento = (100 - descuento)/100;
		total = total * descuento;
		
		total = Math.round(total * 100) / 100d;
		
		
		pedido.setTotal(total);
		addPedido(pedido);
		
	}
	
	public void crearFactura(int idPedido) {
		
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		
		Configuracion nombre = serviceConfig.getByClave("nombre");
		Configuracion direccion = serviceConfig.getByClave("direccion");
		Configuracion cif = serviceConfig.getByClave("cif");
		Configuracion telefono = serviceConfig.getByClave("telefono");
		
		PDFHeaderFooter.cif = cif.getValor();
		PDFHeaderFooter.empresa = nombre.getValor();
		PDFHeaderFooter.telefono = telefono.getValor();
		PDFHeaderFooter.direccion = direccion.getValor();
		
		Configuracion rutaArchivos = serviceConfig.getByClave("rutaArchivos");
		Pedido pedido = getPedido(idPedido);
		
	    try {      
	    	//Obtenemos la instancia del archivo a utilizar
	    	SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	    	String nombreFichero = "Factura_" + formateador.format(new Date()) + "_" + pedido.getNumFactura() + ".pdf";
	    	
	    	String ruta = rutaArchivos.getValor() + nombreFichero;
	    	
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));
	    	
		    //Para insertar cabeceras/pies en todas las páginas
	    	writer.setPageEvent(new PDFHeaderFooter());
	        
		    //Abrimos el documento para edición
		    documento.open();
		    
		    //PARRAFOS
			Paragraph paragraph = new Paragraph();
			
			Usuario user = serviceUser.getUsuario(pedido.getIdUsuario());
			
			paragraph.add(user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2() + "   Numero Factura: " + pedido.getNumFactura());
		    paragraph.setSpacingAfter(10);
		    paragraph.setSpacingBefore(10);
			paragraph.setAlignment(Element.ALIGN_CENTER);
		    
	    	documento.add(paragraph);
	    	
	    	//TABLA
		    PdfPTable tabla = new PdfPTable(5);
		    
		    
		    Phrase texto = new Phrase("cabecera");
			PdfPCell cabecera = new PdfPCell(texto);
			
			cabecera = new PdfPCell(new Phrase("Producto"));
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
		    tabla.addCell(cabecera);
		    
		    
		    cabecera = new PdfPCell(new Phrase("Precio"));
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
		    tabla.addCell(cabecera);
		    
		    cabecera = new PdfPCell(new Phrase("Unidades"));
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
		    tabla.addCell(cabecera);
		    
		    cabecera = new PdfPCell(new Phrase("Impuesto"));
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
		    tabla.addCell(cabecera);
		    
		    cabecera = new PdfPCell(new Phrase("Total"));
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
		    tabla.addCell(cabecera);
		    
		   
		    ArrayList<DetallePedido> detalles = (ArrayList<DetallePedido>) serviceDetalle.getByIdPedido(idPedido);
		    
		    
		    for(int i = 0; i < detalles.size(); i++) {
		    	
		    	DetallePedido detalle = detalles.get(i);
		    	
		    	Producto produc = serviceProducto.getProducto(detalle.getIdProducto());
		    	
		    	tabla.addCell(produc.getNombre());
		    	
		    	tabla.addCell(Double.toString(detalle.getPrecioUnidad()));
		    	tabla.addCell(Integer.toString(detalle.getUnidades()));
		    	tabla.addCell(Float.toString(detalle.getImpuesto()));
		    	tabla.addCell(Double.toString(detalle.getTotal()));
		    	
		    	tabla.completeRow();
		    	
		    }
		    
		    documento.add(tabla);
		    
		    Paragraph total = new Paragraph();
		    total.add("Total:  " + pedido.getTotal());
		    total.setSpacingBefore(20);
			total.setAlignment(Element.ALIGN_CENTER);
		    
	    	documento.add(total);
	    	
		    documento.close(); //Cerramos el documento
		    writer.close(); //Cerramos writer
		    
		    
		    try {
		        File path = new File(ruta);
		        Desktop.getDesktop().open(path);
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
			
	    } catch (Exception ex) {
	    	ex.getMessage();
	    }
	}
	
	
	public void pedidoEnviado(Pedido pedido) {
		
		pedido.setEstado("Enviado");
		
		Configuracion numFactura = serviceConfig.getByClave("numFactura");
		int num = Integer.parseInt(numFactura.getValor());
		num++;
		
		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        String currentYear = getYearFormat.format(new Date());
		
		String nFactura = currentYear + "-" + num;
		
		pedido.setNumFactura(nFactura);
		
		numFactura.setValor(Integer.toString(num));
		
		serviceConfig.addConfig(numFactura);
		
		addPedido(pedido);
		
	}
}
