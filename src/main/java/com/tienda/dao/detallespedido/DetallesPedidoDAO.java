package com.tienda.dao.detallespedido;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.pedido.Pedido;

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

	@Override
	@Transactional
	public List<DetallesPedido> obtenerDetallesPedido(Pedido pedido) {
		// TODO Auto-generated method stub

		Session session = entityManager.unwrap(Session.class);

		logger.info("miramos en el dao que tiene pedido: " + pedido );
		Query<DetallesPedido> query = session.createQuery("FROM DetallesPedido WHERE pedido = :pedido",
				DetallesPedido.class);
		query.setParameter("pedido", pedido);

		List<DetallesPedido> listaDetallesPedido = query.getResultList();

		logger.info("vemos la lista de detalles de pedido desde el DAO :" + listaDetallesPedido);
		return listaDetallesPedido;
	}

}
