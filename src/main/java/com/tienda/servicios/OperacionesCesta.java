package com.tienda.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.cesta.CestaInterfaceDAO;
import com.tienda.dao.detallespedido.DetallesPedido;
import com.tienda.dao.detallespedido.DetallesPedidoInterfaceDao;
import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.pedido.PedidoInterfaceDAO;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.productos.ProductoInterfaceDAO;
import com.tienda.dao.usuario.Usuario;

@Service
public class OperacionesCesta {

	@Autowired
	private CestaInterfaceDAO cestaDAO;
	@Autowired
	private PedidoInterfaceDAO pedidoDAO;
	@Autowired
	private DetallesPedidoInterfaceDao detallesPedidoDAO;
	@Autowired
	private ProductoInterfaceDAO productoDAO;

	public void insertarCesta(Map<Integer, Cesta> cesta, Usuario usuario) {

		Collection<Cesta> articulos = cesta.values();

		for (Cesta articulo : articulos) {

			articulo.setUsuario(usuario);
			System.out.println("Cesta: " + articulo);
			cestaDAO.insertarCestaBD(articulo);
		}
	}

	public void insertarArticuloCesta(Cesta articulo, Usuario usuario) {

		System.out.println("en el servicio para insertar la cesta por un articulo");

		articulo.setUsuario(usuario);
		cestaDAO.insertarCestaBD(articulo);

	}

	public double calcularImpuestosPorProducto(double precio, int cantidad, double impuesto) {

		return (precio * cantidad * impuesto) / 100.0;

	}

	public void finalizarCompra(Pedido pedido, Usuario usuario) {

		BigDecimal bigDecimal = new BigDecimal(pedido.getTotal());
		BigDecimal resultado = (bigDecimal.setScale(2, RoundingMode.HALF_UP));
		pedido.setTotal(resultado.doubleValue());
		pedido.setUsuario(usuario);
		pedido.setFecha(LocalDateTime.now());
		pedido.setEstado("P.E.");
		pedidoDAO.insertarPedidoBD(pedido);

	}

	public void agregarUltimoDetallesPedido(Map<Integer, Cesta> cesta) {

		
		Pedido pedido = pedidoDAO.getUltimoPedido();
		
		Collection<Cesta> articulos = cesta.values();
		
		for (Cesta articulo : articulos) {
			
			DetallesPedido detallesPedido = new DetallesPedido();
			Producto producto = productoDAO.getProductoId(articulo.getProducto().getId());
			detallesPedido.setPedido(pedido);
			detallesPedido.setProducto(producto);
			detallesPedido.setImpuesto(producto.getImpuesto());
			detallesPedido.setPrecio_unidad(producto.getPrecio());
			detallesPedido.setTotal(producto.getPrecio() * articulo.getCantidad());
			detallesPedido.setUnidades(articulo.getCantidad());
			
			detallesPedidoDAO.insertarDetallesPedidoBD(detallesPedido);
			System.out.println( "Articulo desde el for "+ articulo);
		}
	}
}
