package com.tienda.dao.usuario;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class UsuarioDAO implements UsuarioInterfaceDAO {

	@Autowired
    private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		
		Query<Usuario> consulta = session.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios = consulta.getResultList();
		
		return usuarios;
	}

	@Override
	@Transactional
	public void insertarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		Session session=entityManager.unwrap(Session.class);
		System.out.println("Usario insertado");
//		session.persist(usuario);
//		
	}


}
