package com.tienda.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.cesta.CestaInterfaceDAO;
import com.tienda.dao.detallespedido.DetallesPedido;
import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioDAO;
import com.tienda.dao.usuario.UsuarioInterfaceDAO;
import com.tienda.servicios.EnviarCorreo;
import com.tienda.servicios.OperacionesCesta;
import com.tienda.servicios.OperacionesContraseña;
import com.tienda.servicios.OperacionesPedidos;
import com.tienda.servicios.OperacionesUsuario;
import com.tienda.servicios.SubirArchivos;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

	@Autowired
	private EnviarCorreo servicioEmail;

	@Autowired
	private OperacionesUsuario opeUsuario;

	@Autowired
	private OperacionesCesta opeCesta;

	@Autowired
	private OperacionesPedidos opePedido;

	@Autowired
	static Logger logger = LogManager.getRootLogger();

	@GetMapping("/registro")
	public String formularioRegistro(Model modelo) {

		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("mostrarBotonRegistro", true);

		return "registro";
	}

	@PostMapping("/insertarUsuario")
	public String insertarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult resultado,
			Model modelo) {

		boolean validado = true;

		if (resultado.hasErrors()) {

			validado = false;
		}

		if (OperacionesContraseña.validarContraseña(usuario)) {

			usuario = OperacionesContraseña.encriptarContraseña(usuario);

		} else {

			validado = false;
		}

		Usuario comprobarUsuario = opeUsuario.buscarUsuarioNick(usuario);
		Usuario comprobarEmail = opeUsuario.buscarUsuarioEmail(usuario);

		if (comprobarUsuario != null) {

			modelo.addAttribute("ExisteUsuario", "Nombre de Usuario, ya en uso");

			validado = false;
		}

		if (comprobarEmail != null) {

			modelo.addAttribute("ExisteEmail", "Email ya en uso");

			System.out.println("Llega aqui email");
			validado = false;

		}

		if (validado) {

			opeUsuario.insertarUsuarioPorDAO(usuario);

			if (usuario == null) {

				return "redirect:/";

			}

		}

		modelo.addAttribute("mostrarBotonRegistro", true);
		return "registro";
	}

	@GetMapping("/login")
	public String vistaLogin(HttpSession session, Model modelo) {

		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		return "login";

	}

	@PostMapping("/iniciarSesion")
	public String iniciarSesion(@ModelAttribute("usuario") Usuario usuario, HttpSession session, Model modelo) {

		String comprando = (String) session.getAttribute("comprando");
		Usuario usuarioBD = opeUsuario.buscarUsuarioNick(usuario);
		System.out.println("Usuario base de datos: " + usuarioBD + " usuario mandado " + usuario);
		if (usuarioBD != null) {

			if (OperacionesContraseña.desencriptarContraseña(usuario, usuarioBD)) {

				session.setAttribute("usuario", usuarioBD);
				Map<Integer, Cesta> cesta = (Map<Integer, Cesta>) session.getAttribute("cesta");

				System.out.println("Ver todo lo que contiene el usuario al incioar sesion: " + usuarioBD);
				// RECUPERAR LA CESTA SI TIENE UNA GUARDADA EN LA BD
//				if(cesta != null) {
//					
//					opeCesta.insertarCesta(cesta, usuarioBD);
//				
//				}

				if (usuarioBD.isBaja()) {

					modelo.addAttribute("errorInicio", "Contraseña y/o usuario incorrecto");
					return "login";
				}

				if (comprando != null) {

					session.removeAttribute("comprando");
					return "redirect:/cesta/vercesta";
				}

				if (usuarioBD.getRoles().getId() == 1) {

					return "redirect:/";

				} else {

					return "redirect:/administrador/verlistausuarios";
				}
			}

			modelo.addAttribute("errorInicio", "Contraseña y/o usuario incorrecto");
			return "login";
		}

		modelo.addAttribute("errorInicio", "Contraseña y/o usuario incorrecto");
		return "login";
	}

	@GetMapping("/cerrarsesion")
	public String cerrarSession(HttpSession session) {

		session.invalidate();

		return "redirect:/";
	}

	@GetMapping("/verperfil")
	public String verPerfil(Model modelo, HttpSession session) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");
		modelo.addAttribute("usuario", usuario);

		return "editarperfil";
	}

	@PostMapping("/actualizarusuario")
	public String actualizarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult resultado,
			Model modelo, HttpSession session, @RequestParam("imagenavatar") MultipartFile imagen) throws IOException {

		Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
System.out.println("miramos el usuario al confirmar cambios perfil: " + usuarioSesion);
		boolean validado = true;

		if (resultado.hasErrors()) {

			validado = false;
		}

		// Todo el metodo que manda a la imagen

		if (!imagen.getOriginalFilename().trim().isEmpty()) {

			String nombreOriginal = imagen.getOriginalFilename();

			String extension = SubirArchivos.obtenerExtension(nombreOriginal);

			String nombreArchivo = usuario.getUsuario() + extension;

			usuario.setImagen(nombreArchivo);

			String subirRuta = "src/main/webapp/resources/imagenes/usuarios";

			SubirArchivos.guardarArchivo(subirRuta, nombreArchivo, imagen);
		}

		// Fin de metodo de imagen

		Usuario comprobarUsuario = opeUsuario.buscarUsuarioNick(usuario);
		Usuario comprobarEmail = opeUsuario.buscarUsuarioEmail(usuario);

		if (usuarioSesion.getUsuario().equals(usuario.getUsuario())) {

		} else {

			if (comprobarUsuario != null) {

				modelo.addAttribute("ExisteUsuario", "Nombre de Usuario, ya en uso");

				validado = false;
			}
		}

		if (usuarioSesion.getEmail().equals(usuario.getEmail())) {

		} else {

			if (comprobarEmail != null) {

				modelo.addAttribute("ExisteEmail", "Email ya en uso");
				validado = false;

			}

		}

		if (validado) {
			usuario = opeUsuario.actualizarUsuarioService(usuario, usuarioSesion);
			session.setAttribute("usuario", usuario);

			System.out.println("miramso al confirmar cambios :" + usuario);
			return "editarperfil";

		} else {

			return "editarperfil";
		}

	}

	@GetMapping("/cambiarclave")
	public String verCambioClave() {

		return "editarcontraseña";

	}

	@PostMapping("/procesocambiarclave")
	public String cambioClave(HttpSession session, @RequestParam("claveactual") String claveActual,
			@RequestParam("nuevaclave") String nuevaClave,
			@RequestParam("repetirnuevaclave") String repetirNuevaClave) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");
		boolean claveOK = OperacionesContraseña.modificarContraseña(claveActual, usuario);
		usuario.setClave(nuevaClave);
		usuario.setRepetirClave(repetirNuevaClave);

		if (claveOK) {

			usuario = OperacionesContraseña.encriptarContraseña(usuario);
			logger.info("Contraseña cambiada con exito");
			session.setAttribute("usuario", usuario);
			opeUsuario.actualizarClave(usuario);
			return "redirect:/usuario/verperfil";

		}

		return "redirect:/usuario/cambiarclave";
	}

	@GetMapping("/verpedidos")
	public String verPedidos(@RequestParam(name = "ordenarFecha", defaultValue = "DESC") String ordenarFecha,
			Model modelo, HttpSession session) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (modelo.getAttribute("listafecha") != null) {

			List<Pedido> pedidos = (List<Pedido>) modelo.getAttribute("listafecha");

			modelo.addAttribute("pedidos", pedidos);

			logger.info("Vemos que contiene la lista de ver pedidos: " + pedidos);
			return "verpedidos";

		}
		if (ordenarFecha.equals("DESC")) {

		} else {

			ordenarFecha = "ASC";

		}
		logger.info("Llega hasta mandar la id al servicio");
		List<Pedido> pedidos = opeUsuario.sacarListaPedidos(usuario.getId(), ordenarFecha);

		modelo.addAttribute("pedidos", pedidos);

		logger.info("Vemos que contiene la lista de ver pedidos: " + pedidos);
		return "verpedidos";
	}

	@GetMapping("/cancelarpedido")
	public String cancelarPedido(@RequestParam("idpedido") int idPedido, Model modelo, HttpSession session) {

		opeUsuario.cancelarPedidoServicio(idPedido);

		return "redirect:/usuario/verpedidos";
	}

	@GetMapping("/detallespedido")
	public String detallesPedido(@RequestParam("idpedido") int idPedido, Model modelo, HttpSession session) {

		List<DetallesPedido> listaDetallesPedido = opeUsuario.getDetallesPedido(idPedido);
		modelo.addAttribute("listaDetallesPedido", listaDetallesPedido);

		return "detallespedido";
	}

	@GetMapping("/borrarusuario")
	public String borrarUsuario(@RequestParam("idusuario") int idUsuario, Model modelo, HttpSession session) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		opeUsuario.borrarUsuarioID(idUsuario);
		if (usuario.getRoles().getId() == 1) {

			cerrarSession(session);
			return "redirect:/";
		}

		return "redirect:/administrador/verlistausuarios";
	}

	@PostMapping("/filtrarfecha")
	public String filtrarFecha(@RequestParam("fechaDesde") LocalDate fechaDesde,
			@RequestParam("fechaHasta") LocalDate fechaHasta, Model modelo, HttpSession session,
			RedirectAttributes redirigir) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		List<Pedido> listaFecha = opePedido.servicioFiltrarFecha(usuario.getId(), fechaDesde, fechaHasta);

		redirigir.addFlashAttribute("listafecha", listaFecha);

		return "redirect:/usuario/verpedidos";
	}

	@GetMapping("/contactanos")
	public String contactanos() {

		return "contactanos";
	}

	@PostMapping("/enviarCorreo")
	public String enviarCorreo(@RequestParam("remitente") String remitente, @RequestParam("asunto") String asunto,
			@RequestParam("cuerpo") String cuerpo, HttpSession session) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");
		servicioEmail.enviarEmail(remitente, asunto, cuerpo);
		servicioEmail.llegadaMensaje(asunto, usuario);
		return "redirect:/usuario/contactanos?enviado=true";
	}
	
	@GetMapping("/verfactura")
	public String verFactura(@RequestParam("idpedido") int idPedido, Model modelo, HttpSession session) {
		
		Pedido pedido = opePedido.servicioGetPedido(idPedido);
		modelo.addAttribute("pedido", pedido);
		List<DetallesPedido> listaDetallesPedido = opeUsuario.getDetallesPedido(idPedido);
		modelo.addAttribute("listaDetallesPedido", listaDetallesPedido);
		
		return "factura";
	}
}
