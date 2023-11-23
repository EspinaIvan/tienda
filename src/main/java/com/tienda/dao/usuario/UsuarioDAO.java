package com.tienda.dao.usuario;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class UsuarioDAO implements UsuarioInterfaceDAO {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	static Logger logger = LogManager.getRootLogger();

	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);

		Query<Usuario> consulta = session.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios = consulta.getResultList();

		return usuarios;
	}

	@Override
	@Transactional
	public void insertarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub

		Session session = entityManager.unwrap(Session.class);

		session.persist(usuario);

	}

	@Override
	public Usuario buscarUsuarioConUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub

		Session session = entityManager.unwrap(Session.class);

		Query<Usuario> consulta = session.createQuery("from Usuario where usuario = :nombreUsuario", Usuario.class);

		consulta.setParameter("nombreUsuario", nombreUsuario);

		Usuario usuario = consulta.getSingleResultOrNull();

		return usuario;

	}

	@Override
	public Usuario buscarEmailUsuario(String emailUsuario) {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Usuario> consulta = session.createQuery("from Usuario where email = :emailUsuario", Usuario.class);

		consulta.setParameter("emailUsuario", emailUsuario);

		Usuario usuario = consulta.getSingleResultOrNull();

		return usuario;

	}

}
