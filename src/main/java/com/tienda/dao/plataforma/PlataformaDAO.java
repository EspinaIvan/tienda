package com.tienda.dao.plataforma;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class PlataformaDAO implements PlataformaInterfaceDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Plataforma> listaPlataformas() {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		List<Plataforma> listaPlataforma =  session.createQuery("FROM Plataforma", Plataforma.class).getResultList();
		
		return listaPlataforma;
	}

	@Override
	@Transactional
	public void agregarPlataforma(Plataforma plataforma) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		session.merge(plataforma);
		
	}

	@Override
	@Transactional
	public void borrarPlataforma(Plataforma plataforma) {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);
		
		session.remove(plataforma);
		
	}

	@Override
	@Transactional
	public Plataforma getPlataformaID(int idPlataforma) {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);
		
		Plataforma plataforma = session.get(Plataforma.class, idPlataforma);
				
		return plataforma;
	}
	
	

}
