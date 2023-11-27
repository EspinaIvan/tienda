package com.tienda.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.dao.usuario.Usuario;
import com.tienda.servicios.OperacionesUsuario;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {
	
	@Autowired
	static Logger logger = LogManager.getRootLogger();
	@Autowired
	private OperacionesUsuario opeUsuario;
	
	@GetMapping("/verlistausuarios")
	public String verListaUsuarios(HttpSession session, Model modelo) {
		
		List<Usuario> listaUsuarios = opeUsuario.listaUsuario();
		modelo.addAttribute("listausuarios", listaUsuarios);
		
		return "administrador/listausuarios";
	}

}
