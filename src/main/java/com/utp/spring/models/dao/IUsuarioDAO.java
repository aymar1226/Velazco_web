package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
    Optional<Usuario> findByEmail(@Param("correo") String correo);

    @Query("SELECT u FROM Usuario u WHERE u.persona.id = :persona")
    Optional<Usuario> findByPersona(@Param("persona") Long idPersona);






}
