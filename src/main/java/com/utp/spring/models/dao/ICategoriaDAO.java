package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaDAO extends JpaRepository<Categoria,Long>{
}
