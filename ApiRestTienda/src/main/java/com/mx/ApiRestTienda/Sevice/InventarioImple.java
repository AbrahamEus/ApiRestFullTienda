package com.mx.ApiRestTienda.Sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestTienda.Models.Inventario;
import com.mx.ApiRestTienda.Repository.InventarioRepository;

@Service
public class InventarioImple {

	@Autowired
	InventarioRepository inventarioRepository;
	
	@Transactional(readOnly = true)
	public List<Inventario> mostrar(){
		return inventarioRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Inventario buscarHawa(String hawa) {
		return inventarioRepository.findById(hawa).orElse(null);
	}
	
}
