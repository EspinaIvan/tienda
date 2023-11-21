package com.tienda.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioInterfaceDAO;

@Repository
public class OperacionesUsuario {
	
	@Autowired
	private UsuarioInterfaceDAO usuarioDAO;
	
	public void insertarUsuarioPorDAO(Usuario usuario) {
		
		usuario.setFecha_alta(LocalDate.now());
		usuarioDAO.insertarUsuario(usuario);
		
	}
	
	public Usuario buscarUsuarioNick(Usuario usuario) {
		
		String nombreUsuario = usuario.getUsuario();
		usuario = usuarioDAO.buscarUsuarioConUsuario(nombreUsuario);
		
		return usuario;
		
	}
	
	public Usuario buscarUsuarioEmail (Usuario usuario) {
		
		String emailUsuario = usuario.getEmail();
		
		usuario = usuarioDAO.buscarEmailUsuario(emailUsuario);
		
		return usuario;
	}
	
}
