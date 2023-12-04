package com.tienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.roles.Roles;
import com.tienda.dao.roles.RolesInterfaceDAO;

@Service
public class OperacionesAdministrador {

	@Autowired
	private RolesInterfaceDAO rolesDAO;
	
	public List<Roles> servicioGetRoles() {
		
		List<Roles> listaRoles = rolesDAO.getListaRoles();
		
		return listaRoles;
	}
}
