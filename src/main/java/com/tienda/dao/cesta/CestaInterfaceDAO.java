package com.tienda.dao.cesta;

import java.util.List;

public interface CestaInterfaceDAO {

	public void insertarCestaBD(Cesta cesta);

	public List<Cesta> getCesta(int id);
	
}
