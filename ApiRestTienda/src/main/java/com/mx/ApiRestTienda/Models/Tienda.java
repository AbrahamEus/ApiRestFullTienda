package com.mx.ApiRestTienda.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TIENDA")
@Data
public class Tienda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTIENDA")
	private Integer idTienda;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "DIRECCION")
	private String direccion;
	@OneToMany(mappedBy = "tienda",cascade = CascadeType.ALL)
	@JsonIgnore//
	private List<Pedido> list = new ArrayList<>();
}
