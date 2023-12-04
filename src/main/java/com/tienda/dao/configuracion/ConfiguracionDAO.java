package com.tienda.dao.configuracion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ConfiguracionDAO implements ConfiguracionInterfaceDAO {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	static Logger logger = LogManager.getRootLogger();
	
	@Override
	@Transactional
	public Configuracion getNombre() {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		Configuracion configuracion = session.createQuery("FROM Configuracion WHERE clave = :clave", Configuracion.class)
                .setParameter("clave", "nombre")
                .getSingleResult();
		
		return configuracion;
	}

	@Override
	@Transactional
	public Configuracion getLogo() {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		Configuracion configuracion = session.createQuery("FROM Configuracion WHERE clave = :clave", Configuracion.class)
                .setParameter("clave", "logo")
                .getSingleResult();
		
		return configuracion;
	}

	@Override
	@Transactional
	public Configuracion getDireccion() {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		Configuracion configuracion = session.createQuery("FROM Configuracion WHERE clave = :clave", Configuracion.class)
                .setParameter("clave", "direccion")
                .getSingleResult();
		
		return configuracion;
	}

	@Override
	@Transactional
	public Configuracion getCIF() {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		Configuracion configuracion = session.createQuery("FROM Configuracion WHERE clave = :clave", Configuracion.class)
                .setParameter("clave", "CIF")
                .getSingleResult();
		
		return configuracion;
	}

	@Override
	@Transactional
	public Configuracion getEmail() {
		// TODO Auto-generated method stub
		
Session session = entityManager.unwrap(Session.class);
		
		Configuracion configuracion = session.createQuery("FROM Configuracion WHERE clave = :clave", Configuracion.class)
                .setParameter("clave", "Email")
                .getSingleResult();
		
		return configuracion;
	}

}
