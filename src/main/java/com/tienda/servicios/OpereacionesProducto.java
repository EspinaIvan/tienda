package com.tienda.servicios;

import java.time.LocalDate;

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

	public void editarProducto(Producto producto) {
		// TODO Auto-generated method stub
		Producto productoBD = productoDAO.getProductoId(producto.getId());
		producto.setFecha_alta(productoBD.getFecha_alta());
		if(producto.getImagen() == null) {
			
			producto.setImagen(productoBD.getImagen());
			
		}
		productoDAO.actualizarProducto(producto);
	}

	public void registarProducto(Producto producto) {
		// TODO Auto-generated method stub
		
		producto.setFecha_alta(LocalDate.now());
		producto.setBaja(false);
		productoDAO.actualizarProducto(producto);
		
	}
}
