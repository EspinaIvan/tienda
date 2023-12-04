package com.tienda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private OperacionesCatalogo opeCatalogo;
	@Autowired
	private ServicioOperacionesConfiguracion opeConfiguracion;
	
	@GetMapping(value = "")
	public String mostrarSaludo(HttpSession session, Model modelo) {
		
		session.setAttribute("nombre", opeConfiguracion.servicioGetNombre());
		session.setAttribute("logo", opeConfiguracion.servicioGetLogo());
		session.setAttribute("direccion", opeConfiguracion.servicioGetDireccion());
		session.setAttribute("CIF", opeConfiguracion.servicioGetCIF());
		session.setAttribute("email", opeConfiguracion.servicioGetEmail());
		
		Map<Integer, Cesta> cesta;
		
		if(session.getAttribute("cesta") == null) {
			
			cesta = new HashMap<>();

		
			
		} else {
			
			cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");
			
		}
		
		Producto productoMasVendido = opeCatalogo.servicioGetMasVendido();
		session.setAttribute("cesta", cesta);
		List<Producto> listaNovedades = opeCatalogo.servicioObtenerNovedades();
		modelo.addAttribute("listanovedades", listaNovedades);
		modelo.addAttribute("masvendido", productoMasVendido);
		return "index";
	}

	@GetMapping("/catalogo")
	public String mostrarCatalogo(Model modelo) {
		
		return "catalogo";
	}
	
}
