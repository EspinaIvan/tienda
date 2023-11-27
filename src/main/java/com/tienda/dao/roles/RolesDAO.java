package com.tienda.dao.roles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.productos.Producto;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Repository
public class RolesDAO implements RolesInterfaceDAO {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	static Logger logger = LogManager.getRootLogger();
	
	
	@Override
	@Transactional
	public Roles getRol(int idRol) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Roles rol = session.get(Roles.class, idRol);
		
		return rol;
	}

}
