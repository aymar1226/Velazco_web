package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Carrito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoDao extends JpaRepository<Carrito,Long> {

    @Query(
            value = "SELECT c.id_carrito, c.usuario_id, c.fecha_creacion, c.fecha_actualiz, c.total " +
                    "FROM carrito c " +
                    "JOIN usuario u ON c.usuario_id = u.id_usuario " +
                    "WHERE u.correo = ?1",
            nativeQuery = true
    )
    Carrito findByEmail(String correo);

}
