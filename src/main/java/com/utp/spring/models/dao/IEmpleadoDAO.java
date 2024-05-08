package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Empleado;
import com.utp.spring.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmpleadoDAO extends JpaRepository<Empleado,Long> {
    @Query(
            value = "SELECT r.nombre FROM usuarios u INNER JOIN roles r ON u.rolID=r.ID WHERE u.ID = ?1",
            nativeQuery = true
    )
    Optional<Usuario> obtenerRol(String correo);
}
