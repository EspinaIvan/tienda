package com.tienda.dao.productos;

import java.util.List;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.plataforma.Plataforma;

public interface ProductoInterfaceDAO {

	public List<Producto> catalogoCompleto();
	public Producto getProductoId (int idProducto);
	public void actualizarProducto(Producto producto);
	public List<Producto> busquedaPalabra(String busqueda);
	public List<Producto> busquedaPlataforma(Plataforma plataforma);
}
