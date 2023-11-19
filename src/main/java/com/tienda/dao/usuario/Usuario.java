package com.tienda.dao.usuario;

import java.util.Date;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Table(name="usuarios")
@Data
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
	private Date fecha_alta;

}
