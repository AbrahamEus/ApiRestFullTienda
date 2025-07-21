package com.mx.ApiRestTienda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestTienda.Models.DetallePedido;

public interface DetallesPedidoRepository extends JpaRepository<DetallePedido, Integer> {

}
