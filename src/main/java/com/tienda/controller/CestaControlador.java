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
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.productos.Producto;
import com.tienda.servicios.OperacionesCatalogo;
import com.tienda.servicios.OperacionesCesta;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cesta")
public class CestaControlador {

	@Autowired
	private OperacionesCatalogo opeCatalogo;
	
	@Autowired OperacionesCesta opeCesta;

	@GetMapping("/vercesta")
	public String verCesta(HttpSession session, Model modelo) {

		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");

		System.out.println("vemos la cesta " + cesta);
		
		double subtotal = 0;
		double impuestos= 0;
		
		if (cesta != null) {

			Collection<Cesta> articulos = cesta.values();
			List<Producto> productosEnCesta = new ArrayList<>();
			
			
			for (Cesta articulo : articulos) {

				
				Producto producto = opeCatalogo.getProductoId(articulo);
				productosEnCesta.add(producto);
				
				subtotal += (producto.getPrecio() * articulo.getCantidad());
				
				impuestos+= opeCesta.calcularImpuestosPorProducto(producto.getPrecio(), articulo.getCantidad(), producto.getImpuesto());
				
			
			}
			
			Pedido pedido = new Pedido();
			modelo.addAttribute("pedido", pedido);
			modelo.addAttribute("vercesta", productosEnCesta);
			modelo.addAttribute("subtotal", subtotal);
			modelo.addAttribute("impuestos", impuestos);
			
			System.out.println("subtotal: " + subtotal + "impuestos: " + impuestos);
			
		} else {

			modelo.addAttribute("cestavacia", "La Cesta esta vacia");	
		}
		
		return "vercesta";
	}
	
	@GetMapping("/borrararticulo")
	public String borrarArticulo(@RequestParam(name="id") int id, HttpSession session) {
		
		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");
		cesta.remove(id);
		
		return "redirect:/cesta/vercesta";
	}
	
	@GetMapping("/modificarcantidad")
	public String modificarCantidad(@RequestParam(name="id") int id, @RequestParam(name="valor") String valor, HttpSession session) {
		
		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");
		
		System.out.println("entramos en modificar cantidad");
		
		if(valor.equals("sumar")) {
			
			
			Cesta cestaRecuperada = cesta.get(id);
			int cantidadCesta = cestaRecuperada.getCantidad();
			cestaRecuperada.setCantidad(cantidadCesta + 1);
			cesta.put(id, cestaRecuperada );
			session.setAttribute("cesta", cesta);
			
		} else if (valor.equals("restar")) {
			
			Cesta cestaRecuperada = cesta.get(id);
			int cantidadCesta = cestaRecuperada.getCantidad();
			
			if((cantidadCesta - 1) >= 1) {
				
				cestaRecuperada.setCantidad(cantidadCesta - 1);
				cesta.put(id, cestaRecuperada );
				session.setAttribute("cesta", cesta);
			}
			
			System.out.println("Restar articulo: "+ cesta + cantidadCesta + cestaRecuperada + "id" + id);
		}
		
		return "redirect:/cesta/vercesta";
	}
}
