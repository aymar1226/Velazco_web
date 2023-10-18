package com.utp.spring.services;

import com.utp.spring.models.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {

    Optional<Rol> findbyId(Long id);
    List<Rol> findAll();
    Rol save(Rol rol);
    void delete(Rol rol);


}
