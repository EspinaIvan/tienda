package com.tienda.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketControlador {

	@MessageMapping("/realizarAccion")
	@SendTo("/topic/alerta")
	public String realizarAccion(String mensaje) {
		System.out.println("Prueba desde metodo");
		
		return "¡Alerta! Se ha realizado una acción.";
	}
	
}
