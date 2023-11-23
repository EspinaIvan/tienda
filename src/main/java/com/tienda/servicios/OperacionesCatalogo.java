package com.tienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.productos.ProductoInterfaceDAO;

@Service
public class OperacionesCatalogo {

	@Autowired
	private ProductoInterfaceDAO productoDAO;
	
	public List<Producto> catalogoCompletoServicio() {
		
		List<Producto> catalogo = productoDAO.catalogoCompleto();
		
		return catalogo;
	}
	
	public Producto getProductoId (Cesta producto) {
		
		
		int idProducto = producto.getProducto().getId();
		
		Producto productoBD = productoDAO.getProductoId(idProducto);
		
		return productoBD;
	}
}
