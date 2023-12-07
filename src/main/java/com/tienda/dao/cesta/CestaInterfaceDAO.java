package com.tienda.dao.cesta;

import java.util.List;

public interface CestaInterfaceDAO {

	public void insertarCestaBD(Cesta cesta);

	public List<Cesta> getCesta(int id);
	
	public Cesta getProductoCesta(int idUsuario, int idProducto);

	public void borrarCesta(int idProducto, int idUsuario);

	public void eliminarCesta(int idUsuario);
	
}
