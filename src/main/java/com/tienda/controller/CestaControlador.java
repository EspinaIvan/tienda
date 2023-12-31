package com.tienda.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.pedido.PedidoInterfaceDAO;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.usuario.Usuario;
import com.tienda.servicios.ComprobarStock;
import com.tienda.servicios.OperacionesCatalogo;
import com.tienda.servicios.OperacionesCesta;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cesta")
public class CestaControlador {

	@Autowired
	private OperacionesCatalogo opeCatalogo;

	@Autowired
	private OperacionesCesta opeCesta;
	
	@Autowired
	private ComprobarStock opeStock;

	@GetMapping("/vercesta")
	public String verCesta(HttpSession session, Model modelo) {

		String mensaje = (String) modelo.asMap().get("noStock");
		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");

		double subtotal = 0;
		double impuestos = 0;

		if (cesta != null) {

			Collection<Cesta> articulos = cesta.values();
			List<Producto> productosEnCesta = new ArrayList<>();

			for (Cesta articulo : articulos) {

				Producto producto = opeCatalogo.getProductoId(articulo);
				productosEnCesta.add(producto);

				subtotal += (producto.getPrecio() * articulo.getCantidad());

				impuestos += opeCesta.calcularImpuestosPorProducto(producto.getPrecio(), articulo.getCantidad(),
						producto.getImpuesto());

			}

			Pedido pedido = new Pedido();
			modelo.addAttribute("pedido", pedido);
			modelo.addAttribute("vercesta", productosEnCesta);
			modelo.addAttribute("subtotal", subtotal);
			modelo.addAttribute("impuestos", impuestos);
			modelo.addAttribute("cestavacia", "La Cesta esta vacia");
			System.out.println("subtotal: " + subtotal + "impuestos: " + impuestos);

		}

		modelo.addAttribute("noStock", mensaje);

		System.out.println("Entra en el if de mensaje de no stock " + mensaje);

		return "vercesta";
	}

	@GetMapping("/borrararticulo")
	public String borrarArticulo(@RequestParam(name = "id") int id, HttpSession session) {

		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");
		cesta.remove(id);


		if (session.getAttribute("usuario") != null) {

			Usuario usuario = (Usuario)session.getAttribute("usuario");
			opeCesta.borrarCesta(id, usuario.getId());
		}

		return "redirect:/cesta/vercesta";
	}

	@GetMapping("/modificarcantidad")
	public String modificarCantidad(@RequestParam(name = "id") int id, @RequestParam(name = "valor") String valor,
			HttpSession session) {

		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");

		System.out.println("entramos en modificar cantidad");

		if (valor.equals("sumar")) {

			Cesta cestaRecuperada = cesta.get(id);
			int cantidadCesta = cestaRecuperada.getCantidad();
			cestaRecuperada.setCantidad(cantidadCesta + 1);
			cesta.put(id, cestaRecuperada);
			session.setAttribute("cesta", cesta);

			opeStock.miramosStock(id);
			if (session.getAttribute("usuario") != null) {

				opeCesta.modificarDesdeCesta(cestaRecuperada);
			}

		} else if (valor.equals("restar")) {

			Cesta cestaRecuperada = cesta.get(id);
			int cantidadCesta = cestaRecuperada.getCantidad();

			if ((cantidadCesta - 1) >= 1) {

				cestaRecuperada.setCantidad(cantidadCesta - 1);
				cesta.put(id, cestaRecuperada);
				session.setAttribute("cesta", cesta);
				
			}

			if (session.getAttribute("usuario") != null) {

				opeCesta.modificarDesdeCesta(cestaRecuperada);
			}

		}

		return "redirect:/cesta/vercesta";
	}

	@PostMapping("/procesarpago")
	public String procesarPago(@ModelAttribute("pedido") Pedido pedido, HttpSession session, Model modelo,
			RedirectAttributes redirigir) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");

		if (usuario == null) {

			session.setAttribute("comprando", "si");

			return "redirect:/usuario/login";

		}

		Collection<Cesta> articulos = cesta.values();

		for (Cesta articulo : articulos) {

			Producto producto = opeCatalogo.getProductoId(articulo);

			if (articulo.getCantidad() > producto.getStock()) {

				String mensaje = "El producto " + producto.getNombre() + " no tiene suficiente stock ("
						+ producto.getStock() + ")";
				redirigir.addFlashAttribute("noStock", mensaje);

				System.out.println("entra en el if de proceso de pago de stock " + mensaje);
				return "redirect:/cesta/vercesta";

			}
		}

		opeCatalogo.modificarStock(cesta);
		opeCesta.finalizarCompra(pedido, usuario.getId());
		session.removeAttribute("cesta");
		opeCesta.agregarUltimoDetallesPedido(cesta);
		opeCesta.eliminarCesta(usuario.getId());

		return "finalizarcompra";
	}

}
