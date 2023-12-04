package com.tienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.configuracion.Configuracion;
import com.tienda.dao.configuracion.ConfiguracionInterfaceDAO;

@Service
public class ServicioOperacionesConfiguracion {

	@Autowired
	private ConfiguracionInterfaceDAO configuracionDAO;

	public Configuracion servicioGetNombre() {

		Configuracion configuracion = configuracionDAO.getNombre();

		return configuracion;

	}

	public Configuracion servicioGetLogo() {

		Configuracion configuracion = configuracionDAO.getLogo();

		return configuracion;

	}

	public Configuracion servicioGetDireccion() {

		Configuracion configuracion = configuracionDAO.getDireccion();

		return configuracion;

	}

	public Configuracion servicioGetCIF() {

		Configuracion configuracion = configuracionDAO.getCIF();

		return configuracion;

	}
	
	public Configuracion servicioGetEmail() {
		
		Configuracion configuracion = configuracionDAO.getEmail();
		
		return configuracion;
	}
}
