package com.mx.ApiRestTienda.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="CAMIONETA")
@Data
public class Camioneta {
	@Id
	@Column(name = "HAWA")
	private String hawa;
	@Column(name = "NOMBRE_MODELO")
	private String nombreModelo;
	@Column(name = "PRECIO_LISTA")
	private Float precioLista;
	@Column(name = "DESCUENTO")
	private Float descuento;	
	@OneToOne(mappedBy = "camioneta",cascade = CascadeType.ALL)
	@JsonIgnore//
	private Inventario inventario;
	@OneToMany(mappedBy = "camioneta",cascade = CascadeType.ALL)
	@JsonIgnore//
	private List<DetallePedido> lista =new ArrayList<>();
}
