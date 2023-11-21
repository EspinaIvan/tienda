package com.tienda.dao.usuario;

import java.time.LocalDate;
import java.util.Date;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



@Entity
@Table(name="usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column (name="id_rol")
	private int id_rol;
	
	@Column (name="usuario")
	@NotBlank(message = "El campo Usuario no puede estar en blanco")
	@NotNull(message = "El campo Usuario no puede estar en blanco")
	@Size (min = 3, message ="El campo Usuario debe tener minimo 3 letras")
	private String usuario;
	
	@Column (name="email")
	@Email(message = "Por favor, introduce una dirección de correo electrónico válida")
    @NotBlank(message = "El campo Email no puede estar en blanco")
	private String email;
	
	@Column (name="clave")
	@Size (min = 8, message ="La Contraseña debe tener minimo 8 letras")
	private String clave;
	
	@Transient 
	@Size (min = 8, message ="La Contraseña debe tener minimo 8 letras")
	private String repetirClave;
	
	@Column (name="nombre")
	@NotBlank(message = "El campo Nombre no puede estar en blanco")
	@NotNull(message = "El campo Nombre no puede estar en blanco")
	@Size (min = 3, message ="El campo Nombre debe tener minimo 3 letras")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El campo Nombre solo puede contener letras")
	private String nombre;
	
	@Column (name="apellido1")
	@NotBlank(message = "El campo Primer Apellido no puede estar en blanco")
	@NotNull(message = "El campo Primer Apellido no puede estar en blanco")
	@Size (min = 3, message ="El campo Primer Apellido debe tener minimo 3 letras")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El campo Nombre solo puede contener letras")
	private String apellido1;
	
	@Column (name="apellido2")
	@NotBlank(message = "El campo Segundo Apellido no puede estar en blanco")
	@NotNull(message = "El campo Segundo Apellido no puede estar en blanco")
	@Size (min = 3, message ="El campo Segundo Apellido debe tener minimo 3 letras")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El campo Nombre solo puede contener letras")
	private String apellido2;
	
	@Column (name="direccion")
	private String direccion;
	
	@Column (name="provincia")
	private String provincia;
	
	@Column (name="localidad")
	private String localidad;
	
	@Column (name="telefono")
	@NotBlank(message = "El campo Telefono no puede estar en blanco")
	@NotNull(message = "El campo Telefono no puede estar en blanco")
	@Pattern(regexp = "\\d{9}", message = "El teléfono debe contener exactamente 9 dígitos")
	private String telefono;
	
	@Column (name="dni")
	@Pattern(regexp = "\\d{8}[A-Za-z]{1}", message = "El DNI debe tener 8 dígitos y la letra")
	@NotBlank(message = "El campo DNI no puede estar en blanco")
	@NotNull(message = "El campo DNI no puede estar en blanco")
	private String dni;
	
	@Column (name="imagen")
	private String imagen;
	
	@Column (name="baja")
	private boolean baja;
	
	@Column (name="fecha_alta")
	private LocalDate fecha_alta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getRepetirClave() {
		return repetirClave;
	}

	public void setRepetirClave(String repetirClave) {
		this.repetirClave = repetirClave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public LocalDate getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", id_rol=" + id_rol + ", usuario=" + usuario + ", email=" + email + ", clave="
				+ clave + ", repetirClave=" + repetirClave + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", direccion=" + direccion + ", provincia=" + provincia + ", localidad="
				+ localidad + ", telefono=" + telefono + ", dni=" + dni + ", imagen=" + imagen + ", baja=" + baja
				+ ", fecha_alta=" + fecha_alta + "]";
	}

	
}
