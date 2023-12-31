package com.tienda.dao.productos;

import java.time.LocalDate;

import com.tienda.dao.plataforma.Plataforma;

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
@Table(name="producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private Integer id_categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_plataforma", referencedColumnName = "id")
	private Plataforma plataforma;
	
	private String nombre;
	
	private String descripcion;
	
	private double precio;
	
	private int stock;
	
	private double impuesto;
	
	private String imagen;
	
	private boolean baja;
	
	private LocalDate fecha_alta;
	
}
