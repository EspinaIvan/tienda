package com.tienda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.dao.productos.Cesta;
import com.tienda.dao.productos.ProductoInterfaceDAO;
import com.tienda.dao.productos.Productos;
import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioDAO;

@Controller
@SessionAttributes({"cesta", "usuario"})
public class Controlador {

	@ModelAttribute("cesta")
    public Map<Integer, Cesta> initArticulosMap() {
		
        return new HashMap<>();
    
	}
	
	@Autowired
	static Logger logger = LogManager.getRootLogger();

	@GetMapping(value = "")
	public String mostrarSaludo(Model modelo) {
		// Agregar datos al modelo
		modelo.addAttribute("mensaje", "Hola desde GIT");
		List<Productos> catalogo = productosDAO.catalogoCompleto();
		modelo.addAttribute("catalogo", catalogo);
		// Devolver el nombre de la vista (sin extensión)

		System.out.println();
		return "index";
	}

	@RequestMapping("/catalogo")
	public String mostrarCatalogo(Model modelo) {
		
		return "catalogo";
	}
	

	@Autowired
	private ProductoInterfaceDAO productosDAO;
}
