package com.mx.ApiRestTienda.Sevice;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestTienda.Models.Inventario;
import com.mx.ApiRestTienda.Models.Pedido;
import com.mx.ApiRestTienda.Repository.PedidoRepository;

@Service
public class PedidoImple {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Transactional
	public List<Pedido> mostrar(){
		return pedidoRepository.findAll();
	}
	@Transactional
	public Pedido guardar(Pedido pedido) {
		if (pedido.getFechaPedido() == null) {
	        pedido.setFechaPedido(LocalDateTime.now());
	    }
	    return pedidoRepository.save(pedido);
	}
	@Transactional
	public void cambiarEstatus(Integer idPedido, String nuevoEstatus) {
	    Pedido pedido = pedidoRepository.findById(idPedido)
	            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

	    if (!pedido.getEstatus().equalsIgnoreCase("pendiente")) {
	        throw new RuntimeException("Solo los pedidos en estado 'pendiente' se pueden modificar");
	    }

	    // Validar 10 minutos
	    LocalDateTime ahora = LocalDateTime.now();
	    LocalDateTime fechaPedido = pedido.getFechaPedido();
	    Duration tiempoTranscurrido = Duration.between(fechaPedido, ahora);

	    if (nuevoEstatus.equalsIgnoreCase("cancelado") && tiempoTranscurrido.toMinutes() > 10) {
	        throw new RuntimeException("No se puede cancelar el pedido después de 10 minutos");
	    }

	    pedido.setEstatus(nuevoEstatus.toLowerCase());
	    pedidoRepository.save(pedido);
	}

}	
