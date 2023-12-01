package com.tienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.usuario.Usuario;

@Service
public class EnviarCorreo {

	@Autowired
	private JavaMailSender javaEmail;
	
	
	public void enviarEmail(String remitente, String asunto, String cuerpo) {
		
		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setFrom(remitente);
		mensaje.setTo("proyectotiendaserbatic@gmail.com");
		mensaje.setSubject(asunto);
		mensaje.setText(cuerpo);
		
		javaEmail.send(mensaje);
		
	}
	
	public void llegadaMensaje (String asunto, Usuario usuario) {
		
		SimpleMailMessage mensaje = new SimpleMailMessage();
		
		mensaje.setFrom("proyectotiendaserbatic@gmail.com");
		mensaje.setTo(usuario.getEmail());
		mensaje.setSubject(asunto);
		
		String cuerpo = "Estimado " + usuario.getUsuario() + ",\n\n"
                + "Hemos recibido su mensaje y queremos agradecerle por ponerse en contacto con nosotros.\n\n"
                + "Le confirmamos que su mensaje ha sido recibido correctamente. Nuestro equipo está trabajando diligentemente para revisar y responder a su consulta lo más rápido posible.\n\n"
                + "Valoramos su paciencia y le aseguramos que nos pondremos en contacto con usted tan pronto como tengamos una respuesta para su pregunta o solicitud.\n\n"
                + "Gracias nuevamente por elegirnos. Su satisfacción es nuestra prioridad.\n\n"
                + "Saludos cordiales,\n\n";
		
		mensaje.setText(cuerpo);
		
		javaEmail.send(mensaje);
	}
	
	public void enviadoPedido(Pedido pedido, Usuario usuario) {
		

		SimpleMailMessage mensaje = new SimpleMailMessage();
		
		mensaje.setFrom("proyectotiendaserbatic@gmail.com");
		mensaje.setTo(usuario.getEmail());
		mensaje.setSubject("Enviado pedido nº: " + pedido.getId());
		
		String cuerpo = "Estimado " + usuario.getUsuario() + ",\n\n"
                + "Hemos enviado su pedido nº: " + pedido.getId()+". \n\n"
                + "y queremos agradecerle por elegirnos para satisfacer sus necesidades.\n\n"
                + "Le confirmamos que su pedido ha sido enviado correctamente.\n\n"
                + "Valoramos su paciencia.\\n\\n\"\r\n"
                + "Gracias nuevamente por confiar en nosotros. Su satisfacción es nuestra principal prioridad.\n\n"
                + "Saludos cordiales,\n\n";
		
		mensaje.setText(cuerpo);
		
		javaEmail.send(mensaje);
	}
	
	public void canceladoPedido (Pedido pedido, Usuario usuario) {
		
	SimpleMailMessage mensaje = new SimpleMailMessage();
		
		mensaje.setFrom("proyectotiendaserbatic@gmail.com");
		mensaje.setTo(usuario.getEmail());
		mensaje.setSubject("Cancelado pedido nº: " + pedido.getId());
		
		String cuerpo = "Estimado " + usuario.getUsuario() + ",\n\n"
				+ "Hemos recibido su solicitud de cancelación de pedido y lamentamos informarle que el pedido ha sido cancelado de acuerdo con su solicitud.\n\n"
		        + "Le confirmamos que la cancelación ha sido procesada correctamente. Si tiene alguna pregunta adicional o necesita asistencia, no dude en ponerse en contacto con nuestro equipo de soporte.\n\n"
		        + "Agradecemos su comprensión y esperamos poder servirle en el futuro. Su satisfacción es nuestra prioridad.\n\n"
		        + "Saludos cordiales,\n\n";
		
		mensaje.setText(cuerpo);
		
		javaEmail.send(mensaje);
	}
}
