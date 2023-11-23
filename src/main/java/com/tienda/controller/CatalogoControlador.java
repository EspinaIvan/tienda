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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.productos.ProductoInterfaceDAO;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.usuario.Usuario;
import com.tienda.servicios.OperacionesCesta;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalogo")
public class CatalogoControlador {

	@Autowired
	private ProductoInterfaceDAO productosDAO;
	
	@GetMapping("/vercatalogo")
	public String mostarCatalogo(HttpSession session, Model modelo) {
		
		Cesta productoCesta = new Cesta();
		List<Producto> catalogo = productosDAO.catalogoCompleto();
		modelo.addAttribute("catalogo", catalogo);
		modelo.addAttribute("producto", productoCesta);
		
		
		
		return "catalogo";
	}
	
	@PostMapping("/añadirProducto")
	public String añadirProducto(@ModelAttribute ("producto") Cesta producto, HttpSession session, Model modelo) {
		
		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");
		
		if (producto.getProducto()!= null) {
			
			if(cesta.containsKey(producto.getProducto().getId())) {
				
				Cesta cestaRecuperada = cesta.get(producto.getProducto().getId());
				int cantidadCesta = cestaRecuperada.getCantidad();
				cestaRecuperada.setCantidad(cestaRecuperada.getCantidad() + producto.getCantidad());
				cesta.put(producto.getProducto().getId(), cestaRecuperada);
				
			} else {
				
				cesta.put(producto.getProducto().getId(), producto);
				
			}
		}
		
		//INSERTAR EL PRODUCTO EN LA CESTA DE LA BD
		
//		if (session.getAttribute("usuario") != null ) {
//			
//			Usuario usuario = (Usuario) session.getAttribute("usuario");
//			
//			OperacionesCesta opeCesta = new OperacionesCesta();
//			
//			opeCesta.insertarArticuloCesta(producto, usuario);
//		}
		session.setAttribute("cesta", cesta);
		return "redirect:/catalogo/vercatalogo";
	}
	
	
}
