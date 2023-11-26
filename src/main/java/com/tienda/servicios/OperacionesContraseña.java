package com.tienda.servicios;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import com.tienda.dao.usuario.Usuario;

@Service
public class OperacionesContraseña {

	public static boolean validarContraseña(Usuario usuario) {

		if (usuario.getClave().equals(usuario.getRepetirClave())) {

			return true;

		} else {

			return false;
		}

	}

	public static Usuario encriptarContraseña(Usuario usuario) {

		StrongPasswordEncryptor encriptado = new StrongPasswordEncryptor();

		usuario.setClave(encriptado.encryptPassword(usuario.getClave()));

		return usuario;
	}

	public static boolean desencriptarContraseña(Usuario usuario, Usuario usuarioBD) {

		StrongPasswordEncryptor encriptado = new StrongPasswordEncryptor();

		if (encriptado.checkPassword(usuario.getClave(), usuarioBD.getClave())) {

			System.out.println("Contraseñas coinciden");

			return true;
		}

		return false;
	}

	public static boolean modificarContraseña(String clave, Usuario usuarioBD) {

		StrongPasswordEncryptor encriptado = new StrongPasswordEncryptor();

		if (encriptado.checkPassword(clave, usuarioBD.getClave())) {

			System.out.println("Contraseñas coinciden desde cambio de contraseña");

			return true;
		}

		return false;
	}
}
