package com.utp.spring.services;

import com.utp.spring.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Optional<Usuario> findbyId(Long id);
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);

    Optional<Usuario> findByEmail(String correo);

}
