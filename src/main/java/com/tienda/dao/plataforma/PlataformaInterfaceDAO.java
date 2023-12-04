package com.tienda.dao.plataforma;

import java.util.List;

public interface PlataformaInterfaceDAO {

	public List<Plataforma> listaPlataformas();

	public void agregarPlataforma(Plataforma plataforma);
	
	public void borrarPlataforma(Plataforma plataforma);
	
	public Plataforma getPlataformaID(int idPlataforma);
	
}
