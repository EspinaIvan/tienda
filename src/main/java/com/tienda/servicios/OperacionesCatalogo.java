package com.tienda.servicios;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.plataforma.Plataforma;
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
	
	public void modificarStock(Map<Integer, Cesta> cesta) {
		
		Collection<Cesta> articulos = cesta.values();
		
		for (Cesta articulo : articulos) {

			Producto producto = productoDAO.getProductoId(articulo.getProducto().getId());
			producto.setStock(producto.getStock() - articulo.getCantidad());
			productoDAO.actualizarProducto(producto);
			
		}
		
	}

	public Producto getProductoPorId(int id) {
		// TODO Auto-generated method stub
		
		Producto producto = productoDAO.getProductoId(id);
		
		return producto;
	}

	public List<Producto> servicioBusquedaPalabra(String busqueda) {
		// TODO Auto-generated method stub
		
		List<Producto> listaBusqueda = productoDAO.busquedaPalabra(busqueda);
		return listaBusqueda;
	}

	public List<Producto> servicioBusquedaPlataforma(int idPlataforma) {
		// TODO Auto-generated method stub
		Plataforma plataforma = new Plataforma();
		plataforma.setId(idPlataforma);
		List<Producto> listaBusqueda = productoDAO.busquedaPlataforma(plataforma);
		
		return listaBusqueda;
	}
}
