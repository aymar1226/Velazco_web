package com.utp.spring.models.dao;

import com.utp.spring.models.entity.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleOrdenDAO extends JpaRepository<DetalleOrden, Integer> {

}
