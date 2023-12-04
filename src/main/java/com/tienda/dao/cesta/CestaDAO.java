package com.tienda.dao.cesta;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
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

		session.persist(cesta);
		
	}

	@Override
	@Transactional
	public List<Cesta> getCesta(int id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		 
	    List<Cesta> cestaBD = session.createQuery("FROM Cesta WHERE usuario.id = :idUsuario", Cesta.class)
	            .setParameter("idUsuario", id)
	            .getResultList();
		
		return cestaBD;
		
	}

}
