package com.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Entities.Usuario;
import com.tienda.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	
	public Usuario getUsuario(int id) {
		
		return repository.findById(id);
	}
	
	public Usuario getUsuario(String email) {
		return repository.findByEmail(email);
	}
	
	public Usuario validarLogin(String email, String password) {
		
		Usuario user = getUsuario(email);
		
		if(user != null) {
			
			if(UtilService.desencryptedPassword(user.getClave()).equals(password)) {
				
				System.out.print("Usuario no es correcto");
				
				return user;
			}
			
		}else {
			System.out.print("El usuario no es correcto");
		}
		
		user = null;
		
		return user;
	}
}
