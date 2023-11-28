package com.tienda.dao.pedido;

import java.util.List;

import com.tienda.dao.usuario.Usuario;

public interface PedidoInterfaceDAO {

	public void insertarPedidoBD(Pedido pedido);
	public Pedido getUltimoPedido();
	public List<Pedido> listaPedidos(int idUsuario, String ordenarFecha);
	public Pedido getPedidoID(int idPedido);
	public List<Pedido> getListasPedidos();
	public void editarPedido(Pedido pedido);
}
