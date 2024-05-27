package com.utp.spring.services;

import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.entity.Privilegio;
import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Optional<Usuario> findbyId(Long id);
    List<Usuario> findAll();
    @Transactional
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
    Optional<Usuario> findByEmail(String correo);
    String obtenerRolPorId(Long usuarioId);
    Optional<Privilegio> findPrivilegioByEmail(String correo);
    Usuario cambiarPrivilegio(PersonaUsuarioDTO personaUsuarioDTO);
    }
