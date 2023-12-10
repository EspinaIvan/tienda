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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.plataforma.Plataforma;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.usuario.Usuario;
import com.tienda.servicios.ComprobarStock;
import com.tienda.servicios.OperacionesCatalogo;
import com.tienda.servicios.OperacionesCesta;
import com.tienda.servicios.OpereacionesProducto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalogo")
public class CatalogoControlador {

	@Autowired
	static Logger logger = LogManager.getRootLogger();

	@Autowired
	private OperacionesCatalogo opeCatalogo;
	@Autowired
	private OperacionesCesta opeCesta;
	@Autowired
	private OpereacionesProducto opeProducto;
	@Autowired
	private ComprobarStock opeStock;

	@GetMapping("/vercatalogo")
	public String mostarCatalogo(HttpSession session, Model modelo) {

		Map<Integer, Cesta> cesta;

		if (session.getAttribute("cesta") == null) {

			cesta = new HashMap<>();

		} else {

			cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");

		}

		session.setAttribute("cesta", cesta);

		if (modelo.getAttribute("filtro") == null) {

			List<Producto> catalogo = opeCatalogo.catalogoCompletoServicio();
			modelo.addAttribute("catalogo", catalogo);

		} else {

			List<Producto> catalogo = (List<Producto>) modelo.getAttribute("filtro");
			modelo.addAttribute("catalogo", catalogo);

		}

		List<Plataforma> listaPlataformas = opeProducto.servicioListaPlataformas();
		Cesta productoCesta = new Cesta();
		modelo.addAttribute("producto", productoCesta);
		modelo.addAttribute("listaplataformas", listaPlataformas);

		System.out.println("Lista de plataformas: " + listaPlataformas);
		return "catalogo";
	}

	@PostMapping("/añadirProducto")
	public String añadirProducto(@ModelAttribute("producto") Cesta producto, HttpSession session, Model modelo) {

		// Metodo para comprobar el stock

		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");

		if (producto.getCantidad() <= 0) {

			return "redirect:/catalogo/vercatalogo";
		}

		if (producto.getProducto() != null) {

			if (cesta.containsKey(producto.getProducto().getId())) {

				Cesta cestaRecuperada = cesta.get(producto.getProducto().getId());
				int cantidadCesta = cestaRecuperada.getCantidad();
				cestaRecuperada.setCantidad(cestaRecuperada.getCantidad() + producto.getCantidad());
				cesta.put(producto.getProducto().getId(), cestaRecuperada);

			} else {

				cesta.put(producto.getProducto().getId(), producto);

			}
		}

		// INSERTAR EL PRODUCTO EN LA CESTA DE LA BD

		if (session.getAttribute("usuario") != null) {

			Usuario usuario = (Usuario) session.getAttribute("usuario");

			opeCesta.insertarArticuloCesta(producto, usuario);
		}
		opeStock.miramosStock(producto.getProducto().getId());
		session.setAttribute("cesta", cesta);
		return "redirect:/catalogo/vercatalogo";
	}

	@GetMapping("/verproducto")
	public String verProducto(HttpSession session, Model modelo, @RequestParam("id") int id) {

		Cesta productoCesta = new Cesta();
		modelo.addAttribute("producto", productoCesta);
		Producto producto = opeCatalogo.getProductoPorId(id);
		modelo.addAttribute("productoBD", producto);

		return "detalleproducto";
	}

	@PostMapping("/busquedapalabra")
	public String busquedaPalabras(HttpSession session, Model modelo, @RequestParam("busqueda") String busqueda,
			RedirectAttributes redirigir) {

		List<Producto> catalogo = opeCatalogo.servicioBusquedaPalabra(busqueda);
		redirigir.addFlashAttribute("filtro", catalogo);
		return "redirect:/catalogo/vercatalogo";

	}

	@PostMapping("/busquedaselect")
	public String busquedaPlataforma(HttpSession session, Model modelo, @RequestParam("idplataforma") int idPlataforma,
			RedirectAttributes redirigir) {

		System.out.println("idplataforma " + idPlataforma);
		if (idPlataforma != 0) {
			List<Producto> catalogo = opeCatalogo.servicioBusquedaPlataforma(idPlataforma);
			redirigir.addFlashAttribute("filtro", catalogo);
		}
		return "redirect:/catalogo/vercatalogo";
	}
}
