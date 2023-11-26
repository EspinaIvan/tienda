package com.tienda.dao.productos;

import java.util.List;

import com.tienda.dao.cesta.Cesta;

public interface ProductoInterfaceDAO {

	public List<Producto> catalogoCompleto();
	public Producto getProductoId (int idProducto);
	public void actualizarProducto(Producto producto);
}
