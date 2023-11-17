package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EjemploController {

	@GetMapping(value = "/")
	public String mostrarSaludo(Model model) {
		// Agregar datos al modelo
		model.addAttribute("mensaje", "Hola desde GIT2");

		// Devolver el nombre de la vista (sin extensión)
		return "HolaMundo";
	}
	
}
