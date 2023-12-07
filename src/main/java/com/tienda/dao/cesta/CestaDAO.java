package com.tienda.dao.cesta;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class CestaDAO implements CestaInterfaceDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void insertarCestaBD(Cesta cesta) {
		// TODO Auto-generated method stub

		Session session = entityManager.unwrap(Session.class);

		session.merge(cesta);

	}

	@Override
	@Transactional
	public List<Cesta> getCesta(int id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);

		List<Cesta> cestaBD = session.createQuery("FROM Cesta WHERE usuario.id = :idUsuario", Cesta.class)
				.setParameter("idUsuario", id).getResultList();

		return cestaBD;

	}

	@Override
	@Transactional
	public Cesta getProductoCesta(int idUsuario, int idProducto) {
		// TODO Auto-generated method stub

		Session session = entityManager.unwrap(Session.class);

		Cesta articuloBD = session
				.createQuery("FROM Cesta WHERE usuario.id = :idUsuario AND producto.id = :idProducto", Cesta.class)
				.setParameter("idUsuario", idUsuario).setParameter("idProducto", idProducto).uniqueResult();

		return articuloBD;
	}

	@Override
	@Transactional
	public void borrarCesta(int idProducto, int idUsuario) {
		// TODO Auto-generated method stub

		Session session = entityManager.unwrap(Session.class);

		Cesta articuloBD = session
				.createQuery("FROM Cesta WHERE usuario.id = :idUsuario AND producto.id = :idProducto", Cesta.class)
				.setParameter("idUsuario", idUsuario).setParameter("idProducto", idProducto).uniqueResult();

		System.out.println("miramos el articulo que llega para borrar :" + articuloBD);
		session.delete(articuloBD);

	}

	@Override
	@Transactional
	public void eliminarCesta(int idUsuario) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createQuery("DELETE FROM Cesta WHERE usuario.id = :idUsuario").setParameter("idUsuario",
				idUsuario);

		query.executeUpdate();
	}

}
