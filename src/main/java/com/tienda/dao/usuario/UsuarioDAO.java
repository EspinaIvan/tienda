package com.tienda.dao.usuario;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.roles.Roles;

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
	@Transactional
	public Usuario buscarUsuarioConUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub

		Session session = entityManager.unwrap(Session.class);

		Query<Usuario> consulta = session.createQuery("from Usuario where usuario = :nombreUsuario", Usuario.class);

		consulta.setParameter("nombreUsuario", nombreUsuario);

		Usuario usuario = consulta.getSingleResultOrNull();

		return usuario;

	}

	@Override
	@Transactional
	public Usuario buscarEmailUsuario(String emailUsuario) {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Usuario> consulta = session.createQuery("from Usuario where email = :emailUsuario", Usuario.class);

		consulta.setParameter("emailUsuario", emailUsuario);

		Usuario usuario = consulta.getSingleResultOrNull();

		return usuario;

	}

	@Override
	@Transactional
	public void actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.merge(usuario);
		
		System.out.println("usuario Editado con exito");
	}

	@Override
	@Transactional
	public void borrarUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);
		 Usuario usuario = getUsuarioId(idUsuario);
		 usuario.setBaja(true);
		 session.merge(usuario);
		 
	}

	@Override
	@Transactional
	public Usuario getUsuarioId(int idUsuario) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Usuario usuario = session.get(Usuario.class, idUsuario);
		
		return usuario;
	}

	@Override
	@Transactional
	public List<Usuario> getAdministrador() {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);

	    Query<Usuario> query = session.createQuery("SELECT u FROM Usuario u JOIN u.roles r WHERE r.id = :roleId", Usuario.class);
	    query.setParameter("roleId", 3);

	    List<Usuario> administradores = query.getResultList();
		
		return administradores;
	}

}
