package com.tienda.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioDAO;

@Controller
public class Controlador {

	@Autowired
    static Logger logger = LogManager.getRootLogger();
	
	@GetMapping(value = "/")
	public String mostrarSaludo(Model model) {
		// Agregar datos al modelo
		model.addAttribute("mensaje", "Hola desde GIT");
		List<Usuario> listaUsuario = usuarioDAO.getUsuarios();

		model.addAttribute("lista",listaUsuario);
		// Devolver el nombre de la vista (sin extensión)
		return "index";
	}
	

	
	@Autowired
	private UsuarioDAO usuarioDAO;
}
