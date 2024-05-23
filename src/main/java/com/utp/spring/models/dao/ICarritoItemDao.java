package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Cargo;
import com.utp.spring.models.entity.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICarritoItemDao extends JpaRepository<CarritoItem,Long> {
    @Query("SELECT i FROM CarritoItem i JOIN i.carrito c JOIN i.producto p WHERE c.id = :idCarrito AND p.id = :idProducto")
    Optional<CarritoItem> findByCarritoAndProducto(@Param("idCarrito") Long idCarrito, @Param("idProducto") Long idProducto);

    @Query("SELECT i FROM CarritoItem i JOIN i.carrito c WHERE c.id = :idCarrito")
    List<CarritoItem> findByCarrito(@Param("idCarrito") Long idCarrito);
}
