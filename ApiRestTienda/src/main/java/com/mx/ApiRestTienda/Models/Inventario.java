package com.mx.ApiRestTienda.Models;

import com.fasterxml.jackson.core.sym.Name;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "INVENTARIO")
@Data
public class Inventario {
	@Id
	private String id;
	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	@JoinColumn(name = "HAWA" )
	private Camioneta camioneta;
	@Column(name = "EXISTENCIAS")
	private Integer existencias;
	
}
