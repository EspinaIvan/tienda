package com.tienda.dao.pedido;

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
}
