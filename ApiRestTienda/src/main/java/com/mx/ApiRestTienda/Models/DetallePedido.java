package com.mx.ApiRestTienda.Models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DETALLEPEDIDO")
@Data 	
public class DetallePedido {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDDETALLE")
	private Integer idDetalle;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDPEDIDO")
	private Pedido pedido; /* ESTA PROPIEDAD DEBE COINCIDIR CON LO QUE SE AGREGO 
	EN EL MAPPENBY*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "HAWA")
	private Camioneta camioneta; /* ESTA PROPIEDAD DEBE COINCIDIR CON LO QUE SE AGREGO 
	EN EL MAPPENBY*/
	@Column(name = "CANTIDAD")
	private Integer cantidad;
	@Column(name = "PRECIOUNITARIO")
	private double precioUnitario;
	@Column(name = "DESCUENTOPRODUCTO")
	private double descuentoProducto;
	
}
