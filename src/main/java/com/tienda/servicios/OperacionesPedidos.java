package com.tienda.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.pedido.PedidoInterfaceDAO;
import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioInterfaceDAO;

@Service
public class OperacionesPedidos {

	@Autowired
	private PedidoInterfaceDAO pedidoDAO;

	@Autowired
	private UsuarioInterfaceDAO usuarioDAO;
	
	@Autowired
	private EnviarCorreo servicioEmail;
	
	public List<Pedido> listaPedidos() {

		List<Pedido> listaPedidos = pedidoDAO.getListasPedidos();
		Collections.sort(listaPedidos, Comparator.comparing(Pedido::getFecha).reversed());

		return listaPedidos;
	}

	public void enviarPedido(int idPedido) {
		// TODO Auto-generated method stub
		Pedido pedido = pedidoDAO.getPedidoID(idPedido);
		List<Pedido> listaPedidos = pedidoDAO.getListasPedidos();

		Pedido ultimoPedidoConFactura= null;
		int numeroFacturaMasAlto = 0;

		for (Pedido pedidoTemp : listaPedidos) {
			if ("E.".equals(pedidoTemp.getEstado())) {

				Integer numeroFacturaActual = Integer.parseInt(pedidoTemp.getNum_factura());
				 
				
				if (numeroFacturaActual != null && numeroFacturaActual > numeroFacturaMasAlto) {
			        ultimoPedidoConFactura = pedidoTemp;
			        numeroFacturaMasAlto = numeroFacturaActual;
			    }
			}
		}

		if (ultimoPedidoConFactura == null) {

			pedido.setNum_factura("2023000");

		} else {

		
			int numero = (numeroFacturaMasAlto + 1);
			String factura = String.valueOf(numero);
			pedido.setNum_factura(factura);
		}

		Usuario usuario = usuarioDAO.getUsuarioId(pedido.getId_usuario());
		servicioEmail.enviadoPedido(pedido, usuario);
		pedido.setEstado("E.");
		pedidoDAO.editarPedido(pedido);
	}

	public List<Pedido> servicioFiltrarFecha(int id, LocalDate  fechaDesde, LocalDate  fechaHasta) {
		// TODO Auto-generated method stub
		
		LocalDateTime fechaDesdeInicio = fechaDesde.atStartOfDay();
	    LocalDateTime fechaHastaFin = fechaHasta.atTime(LocalTime.MAX);
	    
		List<Pedido> listaFecha = pedidoDAO.filtarFecha(id, fechaDesdeInicio, fechaHastaFin);
		
		return listaFecha;
	}
	
}
