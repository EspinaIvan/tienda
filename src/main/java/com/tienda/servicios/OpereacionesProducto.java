package com.tienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.productos.Producto;
import com.tienda.dao.productos.ProductoInterfaceDAO;

@Service
public class OpereacionesProducto {

	@Autowired
	private ProductoInterfaceDAO productoDAO;

	public void darBajaProducto(int idProducto) {

		Producto producto = productoDAO.getProductoId(idProducto);
		producto.setBaja(true);
		productoDAO.actualizarProducto(producto);
	}

	public void darAltaProducto(int idProducto) {
		
		Producto producto = productoDAO.getProductoId(idProducto);
		producto.setBaja(false);
		productoDAO.actualizarProducto(producto);

	}

	public Producto obtenerProducto(int idProducto) {
		
		Producto producto = productoDAO.getProductoId(idProducto);
		
		return producto;
	}
}
