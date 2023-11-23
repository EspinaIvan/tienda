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

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.productos.ProductoInterfaceDAO;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class Controlador {
	
	@Autowired
	static Logger logger = LogManager.getRootLogger();
	
	@GetMapping(value = "")
	public String mostrarSaludo(HttpSession session, Model modelo) {
		// Agregar datos al modelo
		modelo.addAttribute("mensaje", "Hola desde GIT");
		Map<Integer, Cesta> cesta;
		
		if(session.getAttribute("cesta") == null) {
			
			cesta = new HashMap<>();
			
			logger.info("Creacion de la cesta al dectectar que no exite");
			System.out.println("Creacion de la cesta al dectectar que no exite desde Sysout");
		} else {
			
			cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");
			
		}
		session.setAttribute("cesta", cesta);
		List<Producto> catalogo = productosDAO.catalogoCompleto();
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
