package com.tienda.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.cesta.CestaInterfaceDAO;
import com.tienda.dao.detallespedido.DetallesPedido;
import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioDAO;
import com.tienda.dao.usuario.UsuarioInterfaceDAO;
import com.tienda.servicios.OperacionesCesta;
import com.tienda.servicios.OperacionesContraseña;
import com.tienda.servicios.OperacionesUsuario;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

	@Autowired
	private OperacionesUsuario opeUsuario;

	@Autowired
	private OperacionesCesta opeCesta;

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

			return "redirect:/";

		} else {

			modelo.addAttribute("mostrarBotonRegistro", true);
			return "registro";
		}

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

				if (comprando != null) {

					session.removeAttribute("comprando");
					return "redirect:/cesta/vercesta";
				}

				return "redirect:/";

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
			Model modelo, HttpSession session) {

		System.out.println("entramos y comprobamo que tiene el uuario " + usuario);
		Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");

		boolean validado = true;

		if (resultado.hasErrors()) {

			validado = false;
		}

		Usuario comprobarUsuario = opeUsuario.buscarUsuarioNick(usuario);
		Usuario comprobarEmail = opeUsuario.buscarUsuarioEmail(usuario);

		if (usuarioSesion.getUsuario().equals(usuario.getUsuario())) {
			System.out.println("bandera -1");
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
			System.out.println("bandera -2");
			 usuario = opeUsuario.actualizarUsuarioService(usuario, usuarioSesion);
			session.setAttribute("usuario", usuario);

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
	public String verPedidos(@RequestParam(name = "ordenarFecha", defaultValue = "DESC") String ordenarFecha, Model modelo, HttpSession session) {
		
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (ordenarFecha.equals("DESC")) {
			
		} else {
			
			ordenarFecha = "ASC";
			
		}
		logger.info("Llega hasta mandar la id al servicio");
		List<Pedido> pedidos = opeUsuario.sacarListaPedidos(usuario.getId(), ordenarFecha);
		
		modelo.addAttribute("pedidos", pedidos);
		
		return "verpedidos";
	}

	@GetMapping("/cancelarpedido")
	public String cancelarPedido(@RequestParam("idpedido") int idPedido, Model modelo, HttpSession session) {
		
		opeUsuario.cancelarPedidoServicio(idPedido);
		
		return "redirect:/usuario/verpedidos";
	}
	
	@GetMapping("/detallespedido")
	public String detallesPedido(@RequestParam("idpedido") int idPedido, Model modelo, HttpSession session) {
		
		List<DetallesPedido>  listaDetallesPedido = opeUsuario.getDetallesPedido(idPedido);
		modelo.addAttribute("listaDetallesPedido", listaDetallesPedido);
		
		return "detallespedido";
	}
}
