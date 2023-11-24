package com.tienda.dao.detallespedido;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class DetallesPedidoDAO implements DetallesPedidoInterfaceDao {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	static Logger logger = LogManager.getRootLogger();


	@Override
	@Transactional
	public void insertarDetallesPedidoBD(DetallesPedido detallesPedido) {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);
		session.persist(detallesPedido);
		
		logger.info(" Deatalles Pedido insertado en la BD " + detallesPedido);
	}

}
