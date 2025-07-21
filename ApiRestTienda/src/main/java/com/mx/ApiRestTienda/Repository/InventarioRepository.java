package com.mx.ApiRestTienda.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestTienda.Models.Camioneta;
import com.mx.ApiRestTienda.Models.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario,String> {

	Optional<Inventario> findByCamionetaHawa(String hawa);

}
