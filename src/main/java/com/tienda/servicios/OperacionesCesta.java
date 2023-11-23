package com.tienda.servicios;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.cesta.Cesta;
import com.tienda.dao.cesta.CestaInterfaceDAO;
import com.tienda.dao.usuario.Usuario;

@Repository
public class OperacionesCesta {

	@Autowired
	private CestaInterfaceDAO cestaDAO;
	
	public void insertarCesta(Map<Integer, Cesta> cesta, Usuario usuario) {
		
		Collection<Cesta> articulos = cesta.values();

        for (Cesta articulo : articulos) {
        	
        	articulo.setUsuario(usuario);
            System.out.println("Cesta: " + articulo);
            cestaDAO.insertarCestaBD(articulo);
        }
	}
	
	public void insertarArticuloCesta(Cesta articulo, Usuario usuario) {
		
		System.out.println("en el servicio para insertar la cesta por un articulo");
		
		articulo.setUsuario(usuario);
		cestaDAO.insertarCestaBD(articulo);
		
	}
}
