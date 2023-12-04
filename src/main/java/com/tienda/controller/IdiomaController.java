package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.dao.usuario.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("lang")
public class IdiomaController {

	@GetMapping("/cambiarIdioma")
	public String cambiarIdioma(@RequestParam String lang, HttpSession session) {
		session.setAttribute("lang", lang);
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null || usuario.getRoles().getId() == 1) {

			return "redirect:/";

		}

		return "redirect:/administrador/verlistausuarios";
	}
}
