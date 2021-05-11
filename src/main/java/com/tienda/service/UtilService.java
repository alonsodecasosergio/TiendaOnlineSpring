package com.tienda.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.tienda.model.Entities.Producto;
import com.tienda.model.Entities.Provincia;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class UtilService {
	
	public static String encryptedPassword(String password) {
		
		Base64 base64 = new Base64();

		//ENCRIPTAR
		String encriptada = new String(base64.encode(password.getBytes()));
		
		return encriptada;
		
	}
	
	public static String desencryptedPassword(String userPassword) {
		
		Base64 base64 = new Base64();
		String desencriptado = new String(base64.decode(userPassword.getBytes()));
        
       
        return desencriptado;
		
	}
	
	public static ArrayList<String> getAllProvincias() throws IOException{
		
		ArrayList<String> provincias = new ArrayList<String>();
		
		URL url = new URL("https://raw.githubusercontent.com/IagoLast/pselect/master/data/provincias.json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //indicamos por que verbo HTML ejecutaremos la solicitud
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        
        if (conn.getResponseCode() != 200) 
        {
            //si la respuesta del servidor es distinta al codigo 200 lanzaremos una Exception
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        //creamos un StringBuilder para almacenar la respuesta del web service
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = br.read()) != -1)
        {
          sb.append((char) cp);
        }
        
        String output = sb.toString();
        
        Provincia[] listaProvincia = new Gson().fromJson(output, Provincia[].class);
        
        for(Provincia pro : listaProvincia) {
        	
        	provincias.add(pro.getNm());
        	
        }
        
        Collections.sort(provincias);

        conn.disconnect();
		return provincias;
	}
	
	public static void exportarProductos(ArrayList<Producto> productos) {
		
		try {    
            WritableWorkbook book = Workbook.createWorkbook(new File("C:\\Users\\Formacion\\Desktop\\ficheros\\listadoProductos.xls"));   
               
            WritableSheet sheet = book.createSheet("Sheet_1", 0);   
                 
            Label label = new Label(0, 0, "id");      
            sheet.addCell(label);   
            label = new Label(1, 0, "id Categoria");
            sheet.addCell(label);   
            label = new Label(2, 0, "Nombre");
            sheet.addCell(label);   
            label = new Label(3, 0, "Descripcion");
            sheet.addCell(label);   
            label = new Label(4, 0, "Precio");
            sheet.addCell(label);   
            label = new Label(5, 0, "Stock");
            sheet.addCell(label);  
            label = new Label(6, 0, "Fecha Alta");
            sheet.addCell(label);   
            label = new Label(7, 0, "Fecha Baja");
            sheet.addCell(label);   
            label = new Label(8, 0, "Impuesto");
            sheet.addCell(label);
            
            for(int i = 0, j = 1; i < productos.size(); i++, j++) {
            	
            	Producto producto = productos.get(i);
            	
            	jxl.write.Number number = new jxl.write.Number(0, j,  producto.getId());
                sheet.addCell(number);
                
                number = new jxl.write.Number(1,j, producto.getIdCategoria());
                sheet.addCell(number); 
                
                jxl.write.Label labelProducto = new jxl.write.Label(2,j, producto.getNombre());
                sheet.addCell(labelProducto); 
                
                labelProducto = new jxl.write.Label(3, j, producto.getDescripcion());
                sheet.addCell(labelProducto);
                
                number = new jxl.write.Number(4,j, producto.getPrecio());
                sheet.addCell(number); 
                
                number = new jxl.write.Number(5,j, producto.getStock());
                sheet.addCell(number); 
                
                labelProducto = new jxl.write.Label(6,j, producto.getFechaAlta().toString());
                sheet.addCell(labelProducto);
                
                labelProducto = new jxl.write.Label(7,j, producto.getFechaBaja().toString());
                sheet.addCell(labelProducto);
                
                number = new jxl.write.Number(8,j, producto.getImpuesto());
                sheet.addCell(number);
            }  
               
            //add defined all cell above to case.   
            book.write();   
            //close file case.   
            book.close();   
        } catch (Exception e) {   
            e.printStackTrace();   
        }
	}
}
