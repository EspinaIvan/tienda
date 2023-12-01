package com.tienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnviarCorreo {

	@Autowired
	private JavaMailSender javaEmail;
	
	
	public void enviarEmail(String remitente, String destinatario, String asunto, String cuerpo) {
		
		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setFrom(remitente);
		mensaje.setTo(destinatario);
		mensaje.setSubject(asunto);
		mensaje.setText(cuerpo);
		
		javaEmail.send(mensaje);
		
	}
}
