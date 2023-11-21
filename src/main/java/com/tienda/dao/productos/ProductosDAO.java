package com.tienda.dao.productos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.usuario.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ProductosDAO  implements ProductoInterfaceDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Productos> catalogoCompleto() {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Productos> consulta = session.createQuery("from Productos", Productos.class);
		List<Productos> catalogo = consulta.getResultList();


		return catalogo;
	}

}
