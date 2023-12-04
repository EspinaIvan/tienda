package com.tienda.dao.pedido;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoInterfaceDAO {

	public void insertarPedidoBD(Pedido pedido);

	public Pedido getUltimoPedido();

	public List<Pedido> listaPedidos(int idUsuario, String ordenarFecha);

	public Pedido getPedidoID(int idPedido);

	public List<Pedido> getListasPedidos();

	public void editarPedido(Pedido pedido);

	public List<Pedido> filtarFecha(int id, LocalDateTime fechaDesdeInicio, LocalDateTime fechaHastaFin);

	public List<Pedido> obtenerPedidosPendientes(String estadoActual);

	public List<String> getUltimaFactura();
}
