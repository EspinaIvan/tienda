package com.tienda.dao.detallespedido;

import java.util.List;

import com.tienda.dao.pedido.Pedido;

public interface DetallesPedidoInterfaceDao {

	public void insertarDetallesPedidoBD(DetallesPedido detallesPedido);

	public List<DetallesPedido> obtenerDetallesPedido(Pedido pedido);
}
