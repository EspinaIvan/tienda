package com.tienda.dao.configuracion;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.pedido.Pedido;

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

	@Override
	@Transactional
	public List<Configuracion> getConfiguraciones() {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);
	    
	    List<Configuracion> configuraciones = session.createQuery("FROM Configuracion", Configuracion.class)
	            .getResultList();
	    
		return configuraciones;
	}

	@Override
	@Transactional
	public Configuracion getConfiguracionID(int id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		Configuracion configuracion = session.find(Configuracion.class, id);
		
		return configuracion;
	}

	@Override
	@Transactional
	public void editarConfiguracion(Configuracion configuracion) {
		// TODO Auto-generated method stub
		
			Session session = entityManager.unwrap(Session.class);
			
			session.merge(configuracion);
			
	}
}
