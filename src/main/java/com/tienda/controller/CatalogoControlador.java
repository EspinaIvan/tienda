package com.tienda.controller;

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

import com.tienda.dao.productos.Cesta;
import com.tienda.dao.productos.ProductoInterfaceDAO;
import com.tienda.dao.productos.Productos;
import com.tienda.dao.usuario.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalogo")
public class CatalogoControlador {

	@Autowired
	static Logger logger = LogManager.getRootLogger();
	@Autowired
	private ProductoInterfaceDAO productosDAO;
	
	@GetMapping("/vercatalogo")
	public String mostarCatalogo(HttpSession session, Model modelo) {
		
		
		List<Productos> catalogo = productosDAO.catalogoCompleto();
		modelo.addAttribute("catalogo", catalogo);
		
		
		return "catalogo";
	}
	
	@GetMapping("/añadirProducto")
	public String añadirProducto(@ModelAttribute ("cesta") Cesta cesta, HttpSession session, Model modelo) {
		
		Map<Integer, Cesta> cestaSesion = (Map<Integer, Cesta>) session.getAttribute("cesta");
		
		if (cesta.getProductos()!= null) {
			
			if(cestaSesion.containsKey(cesta.getProductos().getId())) {
				//TODO Retocar la logica
				Cesta cestass = cestaSesion.get(cesta.getProductos().getId());
				int cantidadCestaSesion = cestass.getCantidad();
				//int cantidadCestaSesion = (Integer.parseUnsignedInt(cestaSesion.get(cesta.getProductos().getId())));
				//cestaSesion.put(cesta.getProductos().getId(), cestaSesioañadirPron.() )
			}
		}
		
		return "redirect:/catalogo/vercatalogo";
	}
	
	
}
