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
public class ProductoDAO  implements ProductoInterfaceDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Producto> catalogoCompleto() {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Producto> consulta = session.createQuery("from Producto", Producto.class);
		List<Producto> catalogo = consulta.getResultList();


		return catalogo;
	}

}
