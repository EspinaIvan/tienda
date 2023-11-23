package com.tienda.dao.cesta;

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

}
