package com.tienda.servicios;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.pedido.PedidoInterfaceDAO;
import com.tienda.dao.usuario.Usuario;

@Service
public class OperacionesPedidos {

	@Autowired
	private PedidoInterfaceDAO pedidoDAO;

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

		pedido.setEstado("E.");
		pedidoDAO.editarPedido(pedido);
	}
}
