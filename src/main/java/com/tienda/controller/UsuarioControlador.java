package com.tienda.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioDAO;
import com.tienda.dao.usuario.UsuarioInterfaceDAO;
import com.tienda.servicios.OperacionesCesta;
import com.tienda.servicios.OperacionesContraseña;
import com.tienda.servicios.OperacionesUsuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
	
	@RequestMapping("/registro")
	public String formularioRegistro(Model modelo) {

		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("mostrarBotonRegistro", true);

		return "registro";
	}

	@PostMapping("/insertarUsuario")
	public String insertarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult resultado,
			Model modelo) {

		boolean validado = true;

		if (resultado.hasErrors()) {

			validado = false;
		}

		if (OperacionesContraseña.validarContraseña(usuario)) {

			usuario = OperacionesContraseña.encriptarContraseña(usuario);

		} else {

			validado = false;
		}

		Usuario comprobarUsuario = opeUsuario.buscarUsuarioNick(usuario);
		Usuario comprobarEmail = opeUsuario.buscarUsuarioEmail(usuario);

		if (comprobarUsuario != null) {

			modelo.addAttribute("ExisteUsuario", "Nombre de Usuario, ya en uso");

			validado = false;
		}

		if (comprobarEmail != null) {

			modelo.addAttribute("ExisteEmail", "Email ya en uso");

			System.out.println("Llega aqui email");
			validado = false;

		}

		if (validado) {

			opeUsuario.insertarUsuarioPorDAO(usuario);

			return "redirect:/";

		} else {

			modelo.addAttribute("mostrarBotonRegistro", true);
			return "registro";
		}

	}

	@RequestMapping("/login")
	public String vistaLogin(HttpSession session, Model modelo) {

		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		return "login";

	}

	@PostMapping("/iniciarSesion")
	public String iniciarSesion(@ModelAttribute("usuario") Usuario usuario, HttpSession session, Model modelo) {

		Usuario usuarioBD = opeUsuario.buscarUsuarioNick(usuario);
		System.out.println("Usuario base de datos: " + usuarioBD + " usuario mandado " + usuario);
		if (usuarioBD != null) {

			if (OperacionesContraseña.desencriptarContraseña(usuario, usuarioBD)) {

				session.setAttribute("usuario", usuarioBD);
				Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");
				
				
					//RECUPERAR LA CESTA SI TIENE UNA GUARDADA EN LA BD
//				if(cesta != null) {
//					
//					OperacionesCesta opeCesta = new OperacionesCesta();
//					opeCesta.insertarCesta(cesta, usuarioBD);
//					
//					
//					
//				}
				return "redirect:/";

			}

			modelo.addAttribute("errorInicio", "Contraseña y/o usuario incorrecto");
			return "login";
		}

		modelo.addAttribute("errorInicio", "Contraseña y/o usuario incorrecto");
		return "login";
	}
	
	@RequestMapping("/cerrarsesion")
	public String cerrarSession(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}

	@Autowired
	private OperacionesUsuario opeUsuario;
}
