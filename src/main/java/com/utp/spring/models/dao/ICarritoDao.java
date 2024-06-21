package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Carrito;

import com.utp.spring.models.entity.CarritoItem;
import com.utp.spring.models.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICarritoDao extends JpaRepository<Carrito,Long> {

    @Query(
            value = "SELECT c.id_carrito, c.usuario_id, c.fecha_creacion, c.fecha_actualiz, c.total " +
                    "FROM carrito c " +
                    "JOIN usuario u ON c.usuario_id = u.id_usuario " +
                    "WHERE u.correo = ?1",
            nativeQuery = true
    )
    Optional<Carrito> findByEmail(String correo);

    @Query("SELECT it FROM CarritoItem it " +
            "JOIN Carrito c ON c.id=it.carrito.id " +
            "WHERE c.id = :idCarrito"
    )
    List<CarritoItem> findTotal(@Param("idCarrito")Long idCarrito);

}
