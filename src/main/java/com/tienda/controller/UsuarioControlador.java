package com.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioDAO;
import com.tienda.dao.usuario.UsuarioInterfaceDAO;
import com.tienda.servicios.ValidacionContraseña;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

	@RequestMapping("/registro")
	public String formularioRegistro(Model modelo) {

		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);

		return "registro";
	}

	@PostMapping("/insertarUsuario")
	public String insertarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult resultado, Model modelo) {

		if (resultado.hasErrors()) {
			
			return "registro";
		}
		
		if (ValidacionContraseña.validarContraseña(usuario)) {
			
			usuario = ValidacionContraseña.encriptarContraseña(usuario);
			
		} else {
			
			return "/registro";
		}
		
		usuarioDAO.insertarUsuario(usuario);
		return "redirect:/";
	}

	@Autowired
	private UsuarioInterfaceDAO usuarioDAO;
}
