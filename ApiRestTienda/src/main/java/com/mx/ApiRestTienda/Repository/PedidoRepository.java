package com.mx.ApiRestTienda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestTienda.Models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
