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
@Table(name = "AUDITORIA")
@Data
public class Auditoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "IDAUDITORIA")
	private Integer idAuditoria;
	@Column(name = "FECHA_EVENTO")
	private Date fechaEvento;
	@Column(name = "IpUsuario")
	private String ipUsuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDPEDIDO")
	private Pedido pedido;
	@Column(name = "HAWA")
	private String hawa;
	
}
