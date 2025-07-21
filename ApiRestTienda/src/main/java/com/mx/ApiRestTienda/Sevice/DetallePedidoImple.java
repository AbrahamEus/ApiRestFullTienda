package com.mx.ApiRestTienda.Sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestTienda.Models.DetallePedido;
import com.mx.ApiRestTienda.Models.Inventario;
import com.mx.ApiRestTienda.Repository.DetallesPedidoRepository;
import com.mx.ApiRestTienda.Repository.InventarioRepository;

@Service
public class DetallePedidoImple {
	
	@Autowired
	DetallesPedidoRepository detallesPedidoRepository;
	@Autowired
	InventarioRepository inventarioRepository;
	
	@Transactional(readOnly = true)
	public List<DetallePedido> mostrarDe(){
		return detallesPedidoRepository.findAll();
	}
	@Transactional
	public DetallePedido guardar(DetallePedido detallePedido) {
		
		String hawa = detallePedido.getCamioneta().getHawa();
		Integer cantidadSolicitada = detallePedido.getCantidad();
		
		Inventario inventario = inventarioRepository.findByCamionetaHawa(hawa)
				.orElse(null);
				
			inventario.setExistencias(inventario.getExistencias()-cantidadSolicitada);
		
		return detallesPedidoRepository.save(detallePedido);
	}

}
