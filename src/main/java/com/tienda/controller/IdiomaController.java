package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("lang")
public class IdiomaController {

	@PostMapping("/cambiarIdioma")
	public String cambiarIdioma(@RequestParam String lang, HttpSession session) {
		session.setAttribute("lang", lang);
		return "redirect:/";
	}
}
