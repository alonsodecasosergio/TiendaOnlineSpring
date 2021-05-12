package com.tienda.service;

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
	}
	
	public void updateUsuario(Usuario user) {
		repository.save(user);
	}
}
