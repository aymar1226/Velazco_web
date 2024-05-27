package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Privilegio;
import com.utp.spring.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPrivilegioDAO extends CrudRepository<Privilegio,Long> {

    @Query("SELECT pr FROM Privilegio pr WHERE pr.nombre = :privilegio")
    Optional<Privilegio> findByNombre(@Param("privilegio") String privilegio);

    @Query("SELECT u.privilegio FROM Usuario u WHERE u.correo = :correo")
    Optional<Privilegio> findByEmail(@Param("correo") String correo);

}
