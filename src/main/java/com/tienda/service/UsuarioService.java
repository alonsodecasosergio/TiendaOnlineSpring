package com.tienda.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Usuario;
import com.tienda.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Iterable<Usuario> getAll(){
		return repository.findAll();
	}
	
	public Usuario getUsuario(int id) {
		
		return repository.findById(id);
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
	
	public Usuario getUsuario(String email) {
		return repository.findByEmail(email);
	}
	
	public Usuario validarLogin(String email, String password) {
		
		Usuario user = getUsuario(email);
		
		if(user != null) {
			
			if(UtilService.desencryptedPassword(user.getClave()).equals(password)) {
				
				System.out.print("Usuario es correcto");
				
				return user;
			}
			
		}else {
			System.out.print("El usuario no es correcto");
		}
		
		user = null;
		
		return user;
	}
	
	public void addUsuario(Usuario user) {
		
		user.setClave(UtilService.encryptedPassword(user.getClave()));
		
		repository.save(user);
		//enviarEmail(user.getEmail());
	}
	
	public void updateUsuario(Usuario user) {
		repository.save(user);
	}
	
	public void enviarEmail(String email) {
		
		try {
			// Propiedades de la conexion
			Properties prop = new Properties();
			// Nombre del servidor de salida
			prop.setProperty("mail.smtp.host", "smtp.gmail.com");
			// Habilitamos TLS
			prop.setProperty("mail.smtp.starttls.enable", "true");
			// Indicamos el puerto
			prop.setProperty("mail.smtp.port", "587");
			// Indicamos el usuario
			prop.setProperty("mail.smtp.user", "tiendaonlineserbatic@gmail.com");
			// Indicamos que requiere autenticación
			prop.setProperty("mail.smtp.auth", "true");

			// Creamos un objeto sesion
			Session sesion = Session.getDefaultInstance(prop);
			//TODO
			sesion.setDebug(true);
			// Creamos un objeto mensaje a traves de la sesion
			MimeMessage mensaje = new MimeMessage(sesion);
			
			// Indicamos la cuenta desde la que se va a enviar
			mensaje.setFrom(new InternetAddress("tiendaonlineserbatic@gmail.com"));

			// Añadimos el recipiente al mensaje al que va a ir dirigido el mensaje
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			// Creamos el asunto del mensaje
			mensaje.setSubject("Bienvenido a AloShop");

			// Creamos el cuerpo del mensaje
			mensaje.setText("Bienvenido a nuestra tienda online AloShop. ¡Nos vemos pronto!");

//			mensaje.setText(
//					"Esto es una prueba <br> <b>con Java Mail</b>",
//					"UTF-8",
//					"html");
			
			// Utilizamos un objeto transport para hacer el envio indicando el protocolo
			Transport t = sesion.getTransport("smtp");
			// Hacemos la conexion
			t.connect("tiendaonlineserbatic@gmail.com", "serbatic");
			// Enviamos el mensaje
			t.sendMessage(mensaje, mensaje.getAllRecipients());

			// Cerramos la conexion
			t.close();

		} catch (AddressException ex) {
		} catch (MessagingException ex) {
		}
	}
}
