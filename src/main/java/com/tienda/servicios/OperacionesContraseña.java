package com.tienda.servicios;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.tienda.dao.usuario.Usuario;

public class OperacionesContraseña {

	
	public static boolean validarContraseña(Usuario usuario) {
		
		if ( usuario.getClave().equals(usuario.getRepetirClave())) {
			
			return true;
			
		} else {
			
			return false;
		}
		
	}
	
	public static Usuario encriptarContraseña(Usuario usuario) {
		
		StrongPasswordEncryptor encriptado = new StrongPasswordEncryptor();
		
		usuario.setClave( encriptado.encryptPassword(usuario.getClave()));
		
		return usuario;
	}
}
