package com.tienda.dao.menusopciones;

import java.time.LocalDate;

import com.tienda.dao.productos.Producto;
import com.tienda.dao.roles.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opciones_menu")
public class OpcionesMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	private int id_rol;
	
	private String nombre_opcion;
	
	private String url_opcion;
	
}
