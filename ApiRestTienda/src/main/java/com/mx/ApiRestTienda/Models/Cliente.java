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
@Table(name = "CLIENTE")
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "IDCLIENTE")
	private Integer idCliente;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "TELEFONO")
	private String telefono;
	@Column(name = "CORREO")
	private String correo;
	
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
	@JsonIgnore//
	private List<Pedido> list = new ArrayList<>();

}
