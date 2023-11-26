package com.tienda.servicios;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tienda.dao.detallespedido.DetallesPedido;
import com.tienda.dao.detallespedido.DetallesPedidoInterfaceDao;
import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.pedido.PedidoInterfaceDAO;
import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioInterfaceDAO;

import jakarta.validation.Valid;

@Service
public class OperacionesUsuario {
	
	@Autowired
	private UsuarioInterfaceDAO usuarioDAO;
	@Autowired
	private PedidoInterfaceDAO pedidoDAO;
	@Autowired
	private DetallesPedidoInterfaceDao detallesPedidoDAO;
	@Autowired
	static Logger logger = LogManager.getRootLogger();
	
	public void insertarUsuarioPorDAO(Usuario usuario) {
		
		usuario.setFecha_alta(LocalDate.now());
		usuarioDAO.insertarUsuario(usuario);
		
	}
	
	public Usuario buscarUsuarioNick(Usuario usuario) {
		
		String nombreUsuario = usuario.getUsuario();
		usuario = usuarioDAO.buscarUsuarioConUsuario(nombreUsuario);
		
		return usuario;
		
	}
	
	public Usuario buscarUsuarioEmail (Usuario usuario) {
		
		String emailUsuario = usuario.getEmail();
		
		usuario = usuarioDAO.buscarEmailUsuario(emailUsuario);
		
		return usuario;
	}

	public Usuario actualizarUsuarioService(Usuario usuario, Usuario usuarioBD) {
		// TODO Auto-generated method stub
		
		usuarioBD.setNombre(usuario.getNombre());
		usuarioBD.setUsuario(usuario.getUsuario());
		usuarioBD.setApellido1(usuario.getApellido1());
		usuarioBD.setApellido2(usuario.getApellido2());
		usuarioBD.setEmail(usuario.getEmail());
		usuarioBD.setDni(usuario.getDni());
		usuarioBD.setLocalidad(usuario.getLocalidad());
		usuarioBD.setProvincia(usuario.getProvincia());
		usuarioBD.setTelefono(usuario.getTelefono());
		System.out.println("Llega al servicio de editarUsuario");
		usuarioDAO.actualizarUsuario(usuarioBD);
		
		return usuarioBD;
	}

	public void actualizarClave(Usuario usuario) {
		// TODO Auto-generated method stub
		
		usuarioDAO.actualizarUsuario(usuario);
	}

	public List<Pedido> sacarListaPedidos(int idUsuario, String ordenarFecha) {
		
	
		List <Pedido> pedidos = pedidoDAO.listaPedidos(idUsuario, ordenarFecha);
		

		
		return pedidos;
	}

	public void cancelarPedidoServicio(int idPedido) {
		// TODO Auto-generated method stub
		pedidoDAO.cancelarPedido(idPedido);
	}

	public List <DetallesPedido> getDetallesPedido(int idPedido) {
		// TODO Auto-generated method stub
		Pedido pedido = pedidoDAO.getPedidoID(idPedido);
		List<DetallesPedido> listaDetallesPedido = detallesPedidoDAO.obtenerDetallesPedido(pedido);
				
		return listaDetallesPedido;
	}
	
}
