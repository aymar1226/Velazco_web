package com.utp.spring.models.dao;

import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Persistence;

import java.util.Optional;

@Repository
    public interface IPersonaDao extends JpaRepository<Persona,Long> {
    @Query("SELECT new com.utp.spring.models.dto.PersonaUsuarioDTO(p.id AS personaId, p.nombre AS personaNombre, u.id AS usuarioId, pr.nombre AS usuarioPrivilegio, u.correo AS usuarioCorreo, u.password AS usuarioContrasenia, u.estado AS estadoUsuario) " +
            "FROM Persona p " +
            "JOIN p.usuario u " +
            "JOIN u.privilegio pr "+
            "WHERE u.correo = :correo ")
    Optional<PersonaUsuarioDTO> findByEmail(@Param("correo") String correo);


    /*@Query(
            value = "SELECT p.id_persona , p.nombre, u.correo, u.estado AS estado_usuario " +
                    "FROM persona p " +
                    "JOIN usuario u ON p.id_persona = u.persona_id " +
                    "WHERE u.correo LIKE ?1 AND u.estado = 1",
            nativeQuery = true
    )
    Optional<Persona> findByEmail(String correo);*/

}
