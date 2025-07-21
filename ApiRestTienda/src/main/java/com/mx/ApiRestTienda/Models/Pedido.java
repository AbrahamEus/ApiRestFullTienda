package com.mx.ApiRestTienda.Models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PEDIDO")
@Data
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPEDIDO")
	private Integer idPedido;
	@Column(name = "FECHA_PEDIDO")
	private LocalDateTime fechaPedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDCLIENTE")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDVENDEDOR")
	private Vendedor vendedor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDTIENDA")
	private Tienda tienda;
	
	
	@Column(name = "DESCUENTO_GLOBAL")
	private Float descuentoGlobal;
	@Column(name = "ESTATUS")
	private String estatus;
	
	
	
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
	@JsonIgnore//
	//private List<Auditoria> lista1 =new ArrayList<>();
	private List<DetallePedido> lista =new ArrayList<>();




	
}
