package com.tienda.dao.roles;

import java.time.LocalDate;
import java.util.List;

import com.tienda.dao.menusopciones.OpcionesMenu;
import com.tienda.dao.productos.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private String rol;
	
	@OneToMany
	@JoinColumn(name = "id_rol")
	private List<OpcionesMenu> opcionesMenu;
}
