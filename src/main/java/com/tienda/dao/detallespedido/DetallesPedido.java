package com.tienda.dao.detallespedido;

import java.time.LocalDateTime;

import com.tienda.dao.pedido.Pedido;
import com.tienda.dao.productos.Producto;
import com.tienda.dao.usuario.Usuario;

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
@Table(name="detalles_pedido")
public class DetallesPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", referencedColumnName = "id")
	private Producto producto;
	
	private int unidades;
	
	private double precio_unidad;
	
	private double impuesto;
	
	private double total;
	
}
