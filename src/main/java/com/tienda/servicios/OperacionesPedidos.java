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
}
