package com.tienda.dao.pedido;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.usuario.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class PedidoDAO implements PedidoInterfaceDAO {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	static Logger logger = LogManager.getRootLogger();
	
	@Override
	@Transactional
	public void insertarPedidoBD(Pedido pedido) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.persist(pedido);
		
		logger.info("Pedido insertado en la BD");
	}
	
	@Override
	@Transactional
	public Pedido getUltimoPedido() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Pedido> consulta = session.createQuery("FROM Pedido ORDER BY id DESC", Pedido.class);
		consulta.setMaxResults(1);
		
		Pedido pedido = consulta.uniqueResult();
		
		return pedido;
		
	}

	@Override
	@Transactional
	public List<Pedido> listaPedidos(int idUsuario, String ordenarFecha) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		 String hql = "FROM Pedido p WHERE p.id_usuario = :idusuario ORDER BY p.fecha " + ordenarFecha;
	        Query<Pedido> query = session.createQuery(hql, Pedido.class);
	        query.setParameter("idusuario", idUsuario);
	        
	        List<Pedido> pedidos = query.getResultList();
	        
	        return pedidos;
	}

	@Override
	@Transactional
	public void cancelarPedido(int idPedido) {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);
		Pedido pedido = session.find(Pedido.class, idPedido);
		logger.info("recuperamos el pedido a editar:" + pedido);
		pedido.setEstado("P.C.");
		session.merge(pedido);
	}

	@Override
	@Transactional
	public Pedido getPedidoID(int idPedido) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Pedido pedido = session.find(Pedido.class, idPedido);
		return pedido;
	}
}
