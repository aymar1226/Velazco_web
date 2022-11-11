package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Orden;
import com.utp.spring.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdenDAO extends JpaRepository<Orden, Integer> {
	List<Orden> findByUsuario (Usuario usuario);
}
