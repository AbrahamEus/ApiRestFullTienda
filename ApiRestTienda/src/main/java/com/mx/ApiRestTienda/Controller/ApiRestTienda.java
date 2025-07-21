package com.mx.ApiRestTienda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestTienda.Models.Camioneta;
import com.mx.ApiRestTienda.Models.DetallePedido;
import com.mx.ApiRestTienda.Models.Inventario;
import com.mx.ApiRestTienda.Models.Pedido;
import com.mx.ApiRestTienda.Sevice.DetallePedidoImple;
import com.mx.ApiRestTienda.Sevice.InventarioImple;
import com.mx.ApiRestTienda.Sevice.PedidoImple;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "ApiRestTienda")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiRestTienda {
	
	@Autowired
	InventarioImple inventarioImple;
	
	@Autowired
	DetallePedidoImple detallePedidoImple;
	
	@Autowired
	PedidoImple pedidoImple;
	
	//URL = http://localhost:8080/ApiRestTienda/mostrarD
	@GetMapping("mostrarD")
	public List<DetallePedido> mostrard() {
		return detallePedidoImple.mostrarDe();
	}
	//URL = http://localhost:8080/ApiRestTienda/buscar
	@GetMapping("buscar/{id}")
	public Inventario mostrar(@PathVariable ("id") String id) {
		return inventarioImple.buscarHawa(id);
	}
	@PostMapping("guardar")
	public Pedido  guardar(@RequestBody Pedido pedido) {
		//TODO: process POST request
		return pedidoImple.guardar(pedido);
	}
	@PostMapping("guardarD")
	public DetallePedido  guardarD(@RequestBody DetallePedido detallePedido) {
		//TODO: process POST request
		return detallePedidoImple.guardar(detallePedido);
	}
	@PutMapping("editarEstatus/{id}")
	public ResponseEntity<?> putMethodName(@PathVariable("id") Integer id, @RequestParam("nuevoEstatus") String nuevoEstatus) {
		//TODO: process PUT request
		pedidoImple.cambiarEstatus(id, nuevoEstatus);
		return ResponseEntity.ok("Estatus Actualizado");

	}


	
	
	

}
