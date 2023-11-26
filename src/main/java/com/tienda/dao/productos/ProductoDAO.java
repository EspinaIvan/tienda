package com.tienda.dao.productos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.cesta.Cesta;
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

	@Override
	@Transactional
	public Producto getProductoId(int idProducto) {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);
		Producto producto = session.get(Producto.class, idProducto);
		
		System.out.println("producto: " + producto + idProducto);
		
		return producto;
		
	}

	@Override
	@Transactional
	public void actualizarProducto(Producto producto) {
		// TODO Auto-generated method stub
		
		Session session = entityManager.unwrap(Session.class);

		session.merge(producto);
		
	}



	
}
