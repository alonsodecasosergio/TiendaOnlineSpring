package com.tienda.service;

import java.io.BufferedReader;
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
import com.tienda.model.Entities.Provincia;

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
}
