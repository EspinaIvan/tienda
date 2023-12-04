package com.tienda.dao.configuracion;

import java.util.List;

import org.hibernate.Session;

import com.tienda.dao.pedido.Pedido;

import jakarta.transaction.Transactional;

public interface ConfiguracionInterfaceDAO {

	public Configuracion getNombre();
	public Configuracion getLogo();
	public Configuracion getDireccion();
	public Configuracion getCIF();
	public Configuracion getEmail();
	public List<Configuracion> getConfiguraciones();
	public Configuracion getConfiguracionID(int idPedido);
	public void editarConfiguracion(Configuracion configuracion);
}
