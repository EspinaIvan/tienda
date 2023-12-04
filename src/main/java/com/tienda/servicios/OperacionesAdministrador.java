package com.tienda.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.roles.Roles;
import com.tienda.dao.roles.RolesInterfaceDAO;
import com.tienda.dao.usuario.Usuario;
import com.tienda.dao.usuario.UsuarioInterfaceDAO;

@Service
public class OperacionesAdministrador {

	@Autowired
	private RolesInterfaceDAO rolesDAO;
	@Autowired
	private UsuarioInterfaceDAO usuarioDAO;

	public List<Roles> servicioGetRoles() {

		List<Roles> listaRoles = rolesDAO.getListaRoles();

		return listaRoles;
	}

	public void ComprobarAdministrador() {
		// TODO Auto-generated method stub

		List<Usuario> listaAdministradores = usuarioDAO.getAdministrador();
		System.out.println("miramos la lista de adminsitrsadores: " + listaAdministradores);
		if (listaAdministradores.isEmpty()) {
			
			Roles roles = rolesDAO.getRol(3);
			Usuario usuario = new Usuario();
			usuario.setUsuario("admin");
			usuario.setImagen("usuariodefecto.jpeg");
			usuario.setClave("admin");
			usuario.setEmail("emailacambiar@gmail.com");
			usuario.setFecha_alta(LocalDate.now());
			usuario.setRoles(roles);
			usuario = OperacionesContraseña.encriptarContraseña(usuario);
			usuarioDAO.insertarUsuario(usuario);
			System.out.println("llegamos a mandar al usuario de admin: " + usuario);
		}
	}
}
