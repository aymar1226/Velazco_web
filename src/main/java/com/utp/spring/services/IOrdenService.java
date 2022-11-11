package com.utp.spring.services;

import com.utp.spring.models.entity.Orden;
import com.utp.spring.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IOrdenService {
	List<Orden> findAll();
	Optional<Orden> findById(Integer id);
	Orden save (Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario (Usuario usuario);
}
