package com.tienda.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tienda.dao.detallespedido.DetallesPedido;
import com.tienda.dao.detallespedido.DetallesPedidoInterfaceDao;
import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.pedido.PedidoInterfaceDAO;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.productos.ProductoInterfaceDAO;
import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioInterfaceDAO;

@Service
public class OperacionesPedidos {

	@Autowired
	private PedidoInterfaceDAO pedidoDAO;

	@Autowired
	private UsuarioInterfaceDAO usuarioDAO;

	@Autowired
	private EnviarCorreo servicioEmail;

	@Autowired
	private DetallesPedidoInterfaceDao detallesPedidoDAO;

	@Autowired
	private ProductoInterfaceDAO productoDAO;

	@Value("${procesamiento.habilitado}")
	private boolean ejecutarProcesamiento;

	public List<Pedido> listaPedidos() {

		List<Pedido> listaPedidos = pedidoDAO.getListasPedidos();
		Collections.sort(listaPedidos, Comparator.comparing(Pedido::getFecha).reversed());

		return listaPedidos;
	}

	public void enviarPedido(int idPedido) {
		// TODO Auto-generated method stub
		Pedido pedido = pedidoDAO.getPedidoID(idPedido);
		List<Pedido> listaPedidos = pedidoDAO.getListasPedidos();

		Pedido ultimoPedidoConFactura = null;
		int numeroFacturaMasAlto = 0;

		for (Pedido pedidoTemp : listaPedidos) {
			if ("E.".equals(pedidoTemp.getEstado())) {

				Integer numeroFacturaActual = Integer.parseInt(pedidoTemp.getNum_factura());

				if (numeroFacturaActual != null && numeroFacturaActual > numeroFacturaMasAlto) {
					ultimoPedidoConFactura = pedidoTemp;
					numeroFacturaMasAlto = numeroFacturaActual;
				}
			}
		}

		if (ultimoPedidoConFactura == null) {

			pedido.setNum_factura("2023000");

		} else {

			int numero = (numeroFacturaMasAlto + 1);
			String factura = String.valueOf(numero);
			pedido.setNum_factura(factura);
		}

		Usuario usuario = usuarioDAO.getUsuarioId(pedido.getId_usuario());
		servicioEmail.enviadoPedido(pedido, usuario);
		pedido.setEstado("E.");
		pedidoDAO.editarPedido(pedido);
	}

	public List<Pedido> servicioFiltrarFecha(int id, LocalDate fechaDesde, LocalDate fechaHasta) {
		// TODO Auto-generated method stub

		LocalDateTime fechaDesdeInicio = fechaDesde.atStartOfDay();
		LocalDateTime fechaHastaFin = fechaHasta.atTime(LocalTime.MAX);

		List<Pedido> listaFecha = pedidoDAO.filtarFecha(id, fechaDesdeInicio, fechaHastaFin);

		return listaFecha;
	}

	public void cancelarPedido(int idPedido) {
		// TODO Auto-generated method stub

		Pedido pedido = pedidoDAO.getPedidoID(idPedido);
		List<DetallesPedido> listaDetallesPedido = detallesPedidoDAO.obtenerDetallesPedido(pedido);

		for (DetallesPedido detallesPedido : listaDetallesPedido) {

			Producto producto = detallesPedido.getProducto();
			System.out.println("miramos el producto: " + producto);
			Producto productoBD = productoDAO.getProductoId(producto.getId());
			productoBD.setStock(productoBD.getStock() + detallesPedido.getUnidades());
			productoDAO.actualizarProducto(productoBD);
		}

		Usuario usuario = usuarioDAO.getUsuarioId(pedido.getId_usuario());
		servicioEmail.canceladoPedido(pedido, usuario);
		pedido.setEstado("C.");
		pedidoDAO.editarPedido(pedido);

	}

	public Pedido servicioGetPedido(int idPedido) {
		// TODO Auto-generated method stub

		Pedido pedido = pedidoDAO.getPedidoID(idPedido);

		return pedido;
	}

	public String ultimaFactura() {

		List<String> listaFacturas = pedidoDAO.getUltimaFactura();
		
		if(!listaFacturas.isEmpty()) {
			String ultimaFactura = listaFacturas.get(0);
			
			return ultimaFactura;
		}
		
		return null;
	}

	@Async
	@Scheduled(fixedRate = 120000)
	public void procesarPedidos() {
		
		if (ejecutarProcesamiento) {

			List<Pedido> pedidosPendientes = pedidoDAO.obtenerPedidosPendientes("P.E.");

			System.out.println("miramos pedidosPendientes :" + pedidosPendientes);
			for (Pedido pedido : pedidosPendientes) {

				String ultimaFactura = ultimaFactura();

				System.out.println("ultima factura :" + ultimaFactura);
				if (ultimaFactura != null) {

					Integer numeroFacturaActual = Integer.parseInt(ultimaFactura);
					Integer numeroFactura = numeroFacturaActual + 1;
					String factura = String.valueOf(numeroFactura);
					pedido.setNum_factura(factura);

				} else {

					pedido.setNum_factura("2023000");

				}

				Usuario usuario = usuarioDAO.getUsuarioId(pedido.getId_usuario());
				servicioEmail.enviadoPedido(pedido, usuario);
				pedido.setEstado("E.");
				pedidoDAO.editarPedido(pedido);

			}

		}
	}

}
