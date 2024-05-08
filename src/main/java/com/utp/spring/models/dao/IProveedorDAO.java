package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedorDAO extends JpaRepository<Proveedor,Long> {
}
