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

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.productos.Producto;
import com.tienda.servicios.OperacionesCatalogo;
import com.tienda.servicios.ServicioOperacionesConfiguracion;

import jakarta.servlet.http.HttpSession;

@Controller
public class Controlador {
	
	@Autowired
	static Logger logger = LogManager.getRootLogger();
	@Autowired
	private OperacionesCatalogo opeCatalogo;
	@Autowired
	private ServicioOperacionesConfiguracion opeConfiguracion;
	
	@GetMapping(value = "")
	public String mostrarSaludo(HttpSession session, Model modelo) {
		// Agregar datos al modelo
		
		session.setAttribute("nombre", opeConfiguracion.servicioGetNombre());
		session.setAttribute("logo", opeConfiguracion.servicioGetLogo());
		session.setAttribute("direccion", opeConfiguracion.servicioGetDireccion());
		session.setAttribute("CIF", opeConfiguracion.servicioGetCIF());
		
		System.out.println("Miramos logo: " + opeConfiguracion.servicioGetLogo());
		modelo.addAttribute("mensaje", "Hola desde GIT");
		Map<Integer, Cesta> cesta;
		
		if(session.getAttribute("cesta") == null) {
			
			cesta = new HashMap<>();
			
			logger.info("Creacion de la cesta al dectectar que no exite");
		
			
		} else {
			
			cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");
			
		}
		
		session.setAttribute("cesta", cesta);
		List<Producto> catalogo = opeCatalogo.catalogoCompletoServicio();
		modelo.addAttribute("catalogo", catalogo);
		// Devolver el nombre de la vista (sin extensión)

		System.out.println();
		return "index";
	}

	@GetMapping("/catalogo")
	public String mostrarCatalogo(Model modelo) {
		
		return "catalogo";
	}
	
}
