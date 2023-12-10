package com.tienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tienda.dao.cesta.CestaInterfaceDAO;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.productos.ProductoInterfaceDAO;

import lombok.AllArgsConstructor;
import lombok.Data;



@Service
public class ComprobarStock {

	@Autowired
	private CestaInterfaceDAO cestaDAO;
	@Autowired
	private ProductoInterfaceDAO productoDAO;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	public void miramosStock(int idProducto) {
		
		int totalCestas = cestaDAO.mirarStock(idProducto);
		Producto producto = productoDAO.getProductoId(idProducto);
		
		if (totalCestas >= producto.getStock()) {
			webSocketMensaje socketMensaje = new webSocketMensaje("¡Alerta! Alguien ha añadido " + producto.getNombre() + " y no hay stock suficiente, dese prisa", idProducto);
			messagingTemplate.convertAndSend("/topic/alerta", socketMensaje);
		}
	}
}

@Data
@AllArgsConstructor
class webSocketMensaje {
	
	String mensaje;
	int idProducto;
	
}
