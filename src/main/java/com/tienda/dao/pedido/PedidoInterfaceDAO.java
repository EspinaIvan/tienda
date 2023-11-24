package com.tienda.dao.pedido;

public interface PedidoInterfaceDAO {

	public void insertarPedidoBD(Pedido pedido);
	public Pedido getUltimoPedido();
}
