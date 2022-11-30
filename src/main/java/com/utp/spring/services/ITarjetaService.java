package com.utp.spring.services;

import com.utp.spring.models.entity.Tarjeta;
import com.utp.spring.models.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ITarjetaService {
    Optional<Tarjeta> findbyId(Integer id);
    List<Tarjeta> findAll();
    Tarjeta save(Tarjeta tarjeta);
    void delete(Tarjeta tarjeta);
}
