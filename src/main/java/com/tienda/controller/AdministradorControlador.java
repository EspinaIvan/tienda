package com.tienda.controller;

import java.util.List;

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

import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.pedido.PedidoInterfaceDAO;
import com.tienda.dao.usuario.Usuario;
import com.tienda.servicios.OperacionesContraseña;
import com.tienda.servicios.OperacionesPedidos;
import com.tienda.servicios.OperacionesUsuario;

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

		System.out.println("vemos el usuario antes de mandar el formulario de editar: " + usuario);
		return "administrador/editarusuarioadmin";

	}

	@PostMapping("/editarusuario")
	public String editarUsuarioAdmin(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult resultado,
			Model modelo, HttpSession session) {

		System.out.println(usuario);
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
}
