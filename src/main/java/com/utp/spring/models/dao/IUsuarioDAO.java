package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario,Long> {


    @Query(
            value = "SELECT * FROM usuarios  WHERE usuarios.correo LIKE ?1",
            nativeQuery = true
    )
    Optional<Usuario> findByEmail(String correo);



}
