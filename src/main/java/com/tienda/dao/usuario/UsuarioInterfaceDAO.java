package com.tienda.dao.usuario;

import java.util.List;

import com.tienda.dao.roles.Roles;

public interface UsuarioInterfaceDAO {

	public List<Usuario> getUsuarios();
	public void insertarUsuario(Usuario usuario);
	public Usuario buscarUsuarioConUsuario(String usuario);
	public Usuario buscarEmailUsuario(String email);
	public void actualizarUsuario(Usuario usuario);
}
