package com.utp.spring.services;

import com.utp.spring.models.entity.Privilegio;

import java.util.List;
import java.util.Optional;

public interface IRolService {

    Optional<Privilegio> findbyId(Long id);
    List<Privilegio> findAll();
    Privilegio save(Privilegio privilegio);
    void delete(Privilegio privilegio);


}
