package com.tienda.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tienda.dao.configuracion.Configuracion;
import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.plataforma.Plataforma;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.roles.Roles;
import com.tienda.dao.usuario.Usuario;
import com.tienda.servicios.ComprobarStock;
import com.tienda.servicios.Exportacion;
import com.tienda.servicios.Importacion;
import com.tienda.servicios.OperacionesAdministrador;
import com.tienda.servicios.OperacionesCatalogo;
import com.tienda.servicios.OperacionesContraseña;
import com.tienda.servicios.OperacionesPedidos;
import com.tienda.servicios.OperacionesUsuario;
import com.tienda.servicios.OpereacionesProducto;
import com.tienda.servicios.ServicioOperacionesConfiguracion;
import com.tienda.servicios.SubirArchivos;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {

	@Autowired
	static Logger logger = LogManager.getRootLogger();
	@Autowired
	private OperacionesUsuario opeUsuario;
	@Autowired
	private OperacionesPedidos opePedidos;
	@Autowired
	private OperacionesCatalogo opeCatalogo;
	@Autowired
	private OpereacionesProducto opeProducto;
	@Autowired
	private OperacionesAdministrador opeAdministrador;
	@Autowired
	private ServicioOperacionesConfiguracion opeConfiguracion;
	@Autowired
	private Importacion importar;
	@Autowired
	private SocketControlador socket;
	@Autowired
	private ComprobarStock opeStock;

	@GetMapping("/verlistausuarios")
	public String verListaUsuarios(HttpSession session, Model modelo) {

		List<Usuario> listaUsuarios = opeUsuario.listaUsuario();
		modelo.addAttribute("listausuarios", listaUsuarios);

		return "administrador/listausuarios";
	}

	@GetMapping("/verdetallesusuario")
	public String verDetallesUsuario(@RequestParam("idusuario") int idUsuario, HttpSession session, Model modelo) {

		Usuario usuario = opeUsuario.getUsuarioId(idUsuario);

		modelo.addAttribute("usuario", usuario);

		List<Roles> listaRoles = opeAdministrador.servicioGetRoles();
		modelo.addAttribute("listaroles", listaRoles);

		System.out.println("miurtamos la lista de roles: " + listaRoles);

		return "administrador/editarusuarioadmin";

	}

	@PostMapping("/editarusuario")
	public String editarUsuarioAdmin(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult resultado,
			Model modelo, HttpSession session) {

		Usuario usuarioBD = opeUsuario.getUsuarioId(usuario.getId());
		boolean validado = true;

		if (resultado.hasErrors()) {

			validado = false;
		}

		Usuario comprobarUsuario = opeUsuario.buscarUsuarioNick(usuario);
		Usuario comprobarEmail = opeUsuario.buscarUsuarioEmail(usuario);

		if (usuarioBD.getUsuario().equals(usuario.getUsuario())) {

		} else {

			if (comprobarUsuario != null) {

				modelo.addAttribute("ExisteUsuario", "Nombre de Usuario, ya en uso");

				validado = false;
			}
		}

		if (usuarioBD.getEmail().equals(usuario.getEmail())) {

		} else {

			if (comprobarEmail != null) {

				modelo.addAttribute("ExisteEmail", "Email ya en uso");
				validado = false;

			}

		}

		if (validado) {

			usuario = opeUsuario.actualizarUsuarioService(usuario, usuarioBD);

			return "redirect:/administrador/verlistausuarios";

		} else {

			return "administrador/editarusuarioadmin";
		}

	}

	@GetMapping("/cambiarclaveadmin")
	public String verCambioClave(@RequestParam("idusuario") int idUsuario, Model modelo) {

		modelo.addAttribute("idusuario", idUsuario);

		return "administrador/editarcontraseñaadmin";

	}

	@PostMapping("/cambiarclave")
	public String cambioClave(HttpSession session, @RequestParam("nuevaclave") String nuevaClave,
			@RequestParam("repetirnuevaclave") String repetirNuevaClave, @RequestParam("idusuario") int idUsuario) {

		Usuario usuario = opeUsuario.getUsuarioId(idUsuario);
		usuario.setClave(nuevaClave);
		usuario.setRepetirClave(repetirNuevaClave);

		usuario = OperacionesContraseña.encriptarContraseña(usuario);
		logger.info("Contraseña cambiada con exito");
		opeUsuario.actualizarClave(usuario);
		return "redirect:/administrador/verlistausuarios";

	}

	@GetMapping("/listapedidos")
	public String listaPedidos(HttpSession session, Model modelo) {

		List<Pedido> listaPedidos = opePedidos.listaPedidos();
		modelo.addAttribute("listapedidos", listaPedidos);
		System.out.println("Miramos la lista de pedidos: " + listaPedidos);

		return "administrador/listapedidos";
	}

	@GetMapping("/enviarpedido")
	public String pedidoEnviado(@RequestParam("idpedido") int idPedido, HttpSession session, Model modelo) {

		opePedidos.enviarPedido(idPedido);

		return "redirect:/administrador/listapedidos";
	}

	@GetMapping("/listaproductos")
	public String listaProductos(HttpSession session, Model modelo) {

		List<Producto> listaProductos = opeCatalogo.catalogoCompletoServicio();
		modelo.addAttribute("listaproductos", listaProductos);

		return "administrador/listaproductos";
	}

	@GetMapping("/bajaproducto")
	public String bajaProducto(@RequestParam("idproducto") int idProducto, HttpSession session, Model modelo) {

		opeProducto.darBajaProducto(idProducto);

		return "redirect:/administrador/listaproductos";
	}

	@GetMapping("/altaproducto")
	public String altaProducto(@RequestParam("idproducto") int idProducto, HttpSession session, Model modelo) {

		opeProducto.darAltaProducto(idProducto);

		return "redirect:/administrador/listaproductos";
	}

	@GetMapping("/editarproducto")
	public String editarProducto(@RequestParam("idproducto") int idProducto, HttpSession session, Model modelo) {

		Producto producto = opeProducto.obtenerProducto(idProducto);
		List<Plataforma> listaPlataformas = opeProducto.servicioListaPlataformas();
		modelo.addAttribute("producto", producto);
		modelo.addAttribute("listaplataforma", listaPlataformas);
		return "administrador/editarproducto";
	}

	@PostMapping("/procesarproducto")
	public String procesarPedido(@ModelAttribute("producto") Producto producto,
			@RequestParam("imagenproducto") MultipartFile imagen, Model modelo) throws IOException {

		// Todo el metodo que manda a la imagen

		if (!imagen.getOriginalFilename().trim().isEmpty()) {

			String nombreArchivo = StringUtils.cleanPath(imagen.getOriginalFilename());

			producto.setImagen(nombreArchivo);

			String subirRuta = "src/main/webapp/resources/imagenes/productos";

			SubirArchivos.guardarArchivo(subirRuta, nombreArchivo, imagen);
		}

		// Fin de metodo de imagen

		opeProducto.editarProducto(producto);

		return "redirect:/administrador/listaproductos";

	}

	@GetMapping("/añadirProducto")
	public String agregarProducto(HttpSession session, Model modelo) {

		List<Plataforma> listaPlataformas = opeProducto.servicioListaPlataformas();
		modelo.addAttribute("listaplataforma", listaPlataformas);
		Producto producto = new Producto();
		modelo.addAttribute(producto);

		return "administrador/editarproducto";

	}

	@PostMapping("/ingresarProducto")
	public String registarProducto(@ModelAttribute("producto") Producto producto,
			@RequestParam("imagenproducto") MultipartFile imagen, Model modelo) throws IOException {

		if (!imagen.getOriginalFilename().trim().isEmpty()) {

			String nombreArchivo = StringUtils.cleanPath(imagen.getOriginalFilename());

			producto.setImagen(nombreArchivo);

			String subirRuta = "src/main/webapp/resources/imagenes/productos";

			SubirArchivos.guardarArchivo(subirRuta, nombreArchivo, imagen);

		}

		opeProducto.registarProducto(producto);

		return "redirect:/administrador/listaproductos";

	}

	@GetMapping("/listaplataformas")
	public String listaPlataformas(Model modelo) {

		List<Plataforma> listaPlataformas = opeProducto.servicioListaPlataformas();
		Plataforma plataforma = new Plataforma();
		modelo.addAttribute("plataforma", plataforma);
		modelo.addAttribute("listaplataformas", listaPlataformas);

		return "administrador/listaplataformas";
	}

	@PostMapping("/agregarplataforma")
	public String agregarPlataforma(@ModelAttribute("plataforma") Plataforma plataforma) {

		opeProducto.servicioAgregarPlataforma(plataforma);

		return "redirect:/administrador/listaplataformas";

	}

	@GetMapping("/borrarplataforma")
	public String borrarPlataforma(@RequestParam("idplataforma") int idPlataforma) {

		opeProducto.servicioBorrarPlataforma(idPlataforma);

		return "redirect:/administrador/listaplataformas";
	}

	@GetMapping("/aceptarcancelarpedido")
	public String AceptarCancelarPedido(@RequestParam("idpedido") int idPedido, HttpSession session, Model modelo) {

		opePedidos.cancelarPedido(idPedido);

		return "redirect:/administrador/listapedidos";
	}

	@GetMapping("/configuracion")
	public String Configuracion(HttpSession session, Model modelo) {

		System.out.println("Llega a la configuracion");
		List<Configuracion> configuraciones = opeConfiguracion.servicioGetConfiguraciones();
		modelo.addAttribute("configuraciones", configuraciones);

		return "administrador/configuracion";

	}

	@PostMapping("/cambiarconfiguracion")
	public String CambiarConfiguracion(@ModelAttribute("valor") String valor, @ModelAttribute("id") int id,
			HttpSession session, Model modelo) {

		opeConfiguracion.servicioEditarConfiguracion(valor, id);

		return "redirect:/administrador/configuracion";
	}

	@GetMapping("/primeravezadmin")
	public String PrimeraVezAdmin(HttpSession session, Model modelo) {

		Usuario usuario = (Usuario) session.getAttribute("admin");

		modelo.addAttribute("idusuario", usuario.getId());

		return "administrador/admincambiarclave";
	}

	@PostMapping("/admincambioclave")
	public String CambioClaveAdmin(@RequestParam("nuevaclave") String nuevaClave,
			@RequestParam("repetirnuevaclave") String repetirNuevaClave, @RequestParam("idusuario") int idUsuario,
			HttpSession session, Model modelo) {

		Usuario usuario = opeUsuario.getUsuarioId(idUsuario);
		usuario.setClave(nuevaClave);
		usuario.setRepetirClave(repetirNuevaClave);

		usuario = OperacionesContraseña.encriptarContraseña(usuario);
		logger.info("Contraseña cambiada con exito");
		opeUsuario.actualizarClave(usuario);

		return "redirect:/usuario/login";
	}

	@GetMapping("/exportar")
	public String exportarProductos() throws IOException {

		List<Producto> listaProductos = opeCatalogo.catalogoCompletoServicio();

		Exportacion.exportProductData(listaProductos);

		return "redirect:/administrador/listaproductos";

	}

	@GetMapping("/importar")
	public String importarProductos() throws IOException {

		importar.importarDatosDesdeExcel();

		return "redirect:/administrador/listaproductos";
	}

	@GetMapping("/prueba")
	public String PruebaSocket() {

		opeStock.miramosStock(25);
		return "prueba";

	}

	@GetMapping("/prueba2")
	public String pruebaSocket2() {
		realizarAccion("Prueba mensaje shocket");
		System.out.println("Prueba desde prueba 2");
		return "redirect:/";
	}

	private void realizarAccion(String mensaje) {
		System.out.println("Prueba desde método realizarAccion");


	}
}
