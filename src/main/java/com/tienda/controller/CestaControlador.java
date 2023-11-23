package com.tienda.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.productos.Producto;
import com.tienda.servicios.OperacionesCatalogo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cesta")
public class CestaControlador {

	@Autowired
	private OperacionesCatalogo opeCatalogo;

	@GetMapping("/vercesta")
	public String verCesta(HttpSession session, Model modelo) {

		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");

		if (cesta != null) {

			Collection<Cesta> articulos = cesta.values();
			List<Producto> productosEnCesta = new ArrayList<>();

			for (Cesta articulo : articulos) {

				Producto producto = opeCatalogo.getProductoId(articulo);
				productosEnCesta.add(producto);

			}
			
			modelo.addAttribute("vercesta", productosEnCesta);
			
		} else {

			modelo.addAttribute("cestavacia", "La Cesta esta vacia");	
		}
		
		return "vercesta";
	}
}
